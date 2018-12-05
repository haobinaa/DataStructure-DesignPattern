/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.algorithm.count_str;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.util.Pair;

/**
 *
 * 通过Fork/Join的方式来统计某个字符串在某个文件夹下的文件中出现的次数
 * @author HaoBin
 * @version $Id: SearchStringByForkJoin.java, v0.1 2018/12/5 13:57 HaoBin 
 */
public class SearchStringByForkJoin extends RecursiveTask<List<Pair<String, Integer>>> {
    //任务的阀值
    private static final int THRESHOLD = 5;
    //任务区间的开始值
    private int start;
    //任务区间的结束值
    private int end;
    //所有的路径信息
    private List<Path> allPaths;

    public SearchStringByForkJoin(int start, int end, List<Path> allPaths) {
        this.start = start;
        this.end = end;
        this.allPaths = allPaths;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            List<Path> pathList = Files
                    .walk(Paths.get("D:\\CUST\\WORK\\Exercises\\FullStackTraining\\src\\main\\java\\com\\zkn"), 4)
                    .filter(file -> !Files.isDirectory(file) && file.toString().endsWith("java"))
                    .collect(Collectors.toList());
            SearchStringByForkJoin searchStringByForkJoin = new SearchStringByForkJoin(0, pathList.size(), pathList);
            Future<List<Pair<String, Integer>>> result =
                    forkJoinPool.submit(searchStringByForkJoin);
            List<Pair<String, Integer>> pairList = result.get();
            pairList.stream()
                    .filter(pair -> pair.getValue() > 0)
                    .sorted((s1, s2) -> Integer.compare(s2.getValue(), s1.getValue()))
                    .forEach(pair -> System.out.println(String.format("%d次出现在%s文件中", pair.getValue(), pair.getKey().toString())));
            forkJoinPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main computation performed by this task.
     * @return the result of the computation
     */
    @Override
    protected List<Pair<String, Integer>> compute() {
        List<Pair<String, Integer>> pairList = Lists.newArrayList();
        boolean canCompute = end - start <= THRESHOLD;
        if (canCompute) {
            pairList = IntStream.range(start, end + 1).mapToObj(i -> {
                //到集合的结尾了
                if (i == allPaths.size())
                    return new Pair<>("", 0);
                int sum = 0;
                try {
                    Optional optional = Files.lines(allPaths.get(i)).map(file -> {
                        int count = 0;
                        int flag = file.indexOf("main");
                        if (flag >= 0) {
                            do {
                                count++;
                            } while ((flag = file.indexOf("main", flag + 1)) > 0);
                        }
                        return count;
                    }).reduce(Integer::sum);
                    sum = optional.isPresent() ? (int) optional.get() : 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new Pair<>(allPaths.get(i).toString(), sum);
            }).collect(Collectors.toList());
        } else {
            //取中间值
            int middle = (end + start) / 2;
            //创建Fork子任务
            SearchStringByForkJoin leftFork = new SearchStringByForkJoin(start, middle, allPaths);
            //创建Fork子任务
            SearchStringByForkJoin rightFork = new SearchStringByForkJoin(middle + 1, end, allPaths);
            //执行子任务
            leftFork.fork();
            rightFork.fork();
            //合并子任务的结果集
            pairList.addAll(leftFork.join());
            pairList.addAll(rightFork.join());
        }
        return pairList;
    }
}