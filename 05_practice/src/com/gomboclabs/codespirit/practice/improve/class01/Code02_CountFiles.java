package com.gomboclabs.codespirit.practice.improve.class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_CountFiles {
    public static void main(String[] args) {
        String path = "/Users/macos/Downloads/xxl-job-2.2.0";
        System.out.println("广度优先遍历结果：" + path + "路径下共包含文件数：" + countFiles1(path));
        System.out.println("深度优先遍历结果：" + path + "路径下共包含文件数：" + countFiles2(path));
        System.out.println("test结果：" + path + "路径下共包含文件数：" + getFileNumber(path));
    }

    // 用队列实现，广度优先
    public static int countFiles1(String s) {
        File file = new File(s);
        Queue<String> queue = new LinkedList<>();
        int fileNum = 0;
        if (file.isFile()) {
            return 1;
        } else if (file.isDirectory()) {
            queue.add(s);
        }
        while (!queue.isEmpty()) {
            String s1 = queue.poll();
            File f = new File(s1);
            // 此函数会统计隐藏文件
            File[] files = f.listFiles();
            for (File file1 : files) {
                if (file1.isFile()) {
                    fileNum++;
                } else if (file1.isDirectory()) {
                    queue.add(file1.getPath());
                }
            }
        }
        // 文件不存在（既不是文件夹也不是文件）直接会返回初始值0
        return fileNum;
    }

    // 用栈实现，深度优先
    public static int countFiles2(String s) {
        File file = new File(s);
        Stack<String> stack = new Stack<>();
        int fileNum = 0;
        if (file.isFile()) {
            return 1;
        } else if (file.isDirectory()) {
            stack.push(s);
        }
        while (!stack.isEmpty()) {
            String s1 = stack.pop();
            File f = new File(s1);
            File[] files = f.listFiles();
            for (File file1 : files) {
                if (file1.isFile()) {
                    fileNum++;
                } else if (file1.isDirectory()) {
                    stack.push(file1.getPath());
                }
            }
        }
        return fileNum;
    }

    // fortest
    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }
}
