/*
 Navicat Premium Data Transfer

 Source Server         : xiaoxiaoguai
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : nftmarket

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/03/2023 01:15:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `lastTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最近登录的时间',
  `lastLoginIp` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最后登录的ip',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '头像URL',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (2, '19974712583', '小小怪', '69c5fcebaa65b560eaf06c3fbeb481ae44b8d618', '2022-09-13 12:48:11', NULL, '2023-03-07 17:56:16', '0:0:0:0:0:0:0:1', '/avatar/a8d6dec68fc349f182beb0f77c801b46.jpg');
INSERT INTO `account` VALUES (3, '123456', '123456', '69c5fcebaa65b560eaf06c3fbeb481ae44b8d618', '2022-09-17 15:08:17', NULL, '2023-03-01 18:28:12', '0:0:0:0:0:0:0:1', '/avatar/390dc2e41e284bb7b70eb4c16d568e95.jpg');
INSERT INTO `account` VALUES (4, '123', '123', '69c5fcebaa65b560eaf06c3fbeb481ae44b8d618', '2022-09-27 13:23:41', NULL, '2022-10-06 14:19:15', '0:0:0:0:0:0:0:1', '/avatar/9f6aa117a9cb4ca9885588e3b5812328.jpg');

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '操作账号',
  `actionId` int(12) NULL DEFAULT NULL COMMENT '操作id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作类型（动态、关注、博客）',
  `actionType` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作类型（删除、关注、取关、收藏）',
  `actionTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES (1, '123', 11, 'blog', '点赞', '2022-11-06 01:19:27');

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布账号',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '博客内容',
  `abstractContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容摘要',
  `thumbsUpCount` int(12) NULL DEFAULT 0 COMMENT '点赞数',
  `thumbsDownCount` int(12) NULL DEFAULT 0 COMMENT '踩的次数',
  `visits` int(12) NULL DEFAULT 0 COMMENT '查看次数',
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建时间',
  `publishTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布时间',
  `modified` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '最近一次的修改时间',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '封面',
  `isPublished` int(4) NULL DEFAULT 0 COMMENT '是否已发布（1代表已发布，0代表未发布）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 'hnucm_oj 算法分析与设计 练习13', '19974712583', '# hnucm_oj 算法分析与设计 练习13\n\n[湖中大OJ](http://acm.hnucm.edu.cn/JudgeOnline/)\n\n## 问题 A: 迷路的牛牛\n题目描述\n牛牛去犇犇老师家补课，出门的时候面向北方，但是现在他迷路了。虽然他手里有一张地图，但是他需要知道自己面向哪个方向，请你帮帮他。\n\n输入\n每个输入包含一个测试用例。\n每个测试用例的第一行包含一个正整数，表示转方向的次数N(N<=1000)。\n接下来的一行包含一个长度为N的字符串，由L和R组成，L表示向左转，R表示向右转。\n\n输出\n输出牛牛最后面向的方向，N表示北，S表示南，E表示东，W表示西。\n\n样例输入\n3\nLRR\n\n样例输出\nE\n\n代码：\n\n```java\nimport java.util.Scanner;\n   \npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        while(sc.hasNext()) {\n            int n = sc.nextInt();\n            sc.nextLine();\n            String a = sc.nextLine();\n            char[] b = a.toCharArray();\n            int L=0,R=0,c=0;\n            for(int i=0;i<n;i++) {\n                if(b[i]==\'R\') {\n                    R++;\n                }\n                else {\n                    L++;\n                }\n            }\n            if(R>L) {\n                c=(R-L)%4;\n                if(c==0) {\n                    System.out.println(\"N\");\n                }\n                else if(c==1) {\n                    System.out.println(\"E\");\n                }\n                else if(c==2) {\n                    System.out.println(\"S\");\n                }\n                else if(c==3) {\n                    System.out.println(\"W\");\n                }\n            }\n            else {\n                c=(L-R)%4;\n                if(c==0) {\n                    System.out.println(\"N\");\n                }\n                else if(c==1) {\n                    System.out.println(\"W\");\n                }\n                else if(c==2) {\n                    System.out.println(\"S\");\n                }\n                else if(c==3) {\n                    System.out.println(\"E\");\n                }\n            }\n        }\n    }\n}\n```\n\n## 问题 B: 工作单位\n题目描述\n在某个城市中住着n个人，现在给定关于这n个人的m条信息（即某2个人认识）。 \n假设所有认识的人一定属于同一个单位，请计算该城市有多少个单位？ \n\n输入\n第1行的第1个值表示总人数n，第2个值表示总信息数m；第2行开始为具体的认识关系信息\n\n输出\n单位的个数\n\n样例输入\n10 4\n2 3\n4 5\n4 8\n5 8\n\n样例输出\n7\n\n代码：\n\n```java\nimport java.util.Scanner;\n   \npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        while(sc.hasNext()) {\n            int n = sc.nextInt();\n            int m = sc.nextInt();\n            int[] a = new int[n+1];\n            int count=1;\n            for(int i=0;i<m;i++) {\n                int j = sc.nextInt();\n                int k = sc.nextInt();\n                if(a[j]==0&&a[k]==0) {\n                    a[j]=count;\n                    a[k]=count;\n                    count++;\n                }\n                else if(a[j]!=0&&a[k]==0) {\n                    a[k]=a[j];\n                }\n                else if(a[k]!=0&&a[j]==0) {\n                    a[j]=a[k];\n                }\n                else if(a[j]!=a[k]&&a[j]!=0&&a[k]!=0) {\n                    if(a[j]<a[k]) {\n                        for(int p=1;p<=n;p++) {\n                            if(a[p]==a[k]) {\n                                a[p]=a[j];\n                            }\n                        }\n                    }\n                    else {\n                        for(int p=1;p<=n;p++) {\n                            if(a[p]==a[j]) {\n                                a[p]=a[k];\n                            }\n                        }\n                    }\n                }\n            }\n            int rel=0,flag=0;\n            for(int i=1;i<=n;i++) {\n                if(a[i]==0) {\n                    rel++;\n                }\n            }\n            for(int i=1;i<count;i++) {\n                flag=0;\n                for(int j=1;j<=n;j++) {\n                    if(a[j]==i) {\n                        flag=1;\n                        break;\n                    }\n                }\n                if(flag==1) {\n                    rel++;\n                }\n            }\n            System.out.println(rel);\n        }\n    }\n}\n```\n*PS：并查集用一个数组代替，不同的数字代表不同的集合。*\n\n## 问题 C: 隔离14天\n题目描述\n      ……\n如果实施更为严格的防控措施，一辆汽车上有一个确诊患者或者密切接触者，那么该汽车上所有的人都被认为是密切接触者，全部需要自行居家隔离或者集中隔离14天。\n      现在假定编号为0的乘客冠状病毒核酸检验呈阳性，请编写一个程序统计需隔离的总人数（包括编号为0的乘客）。 \n\n输入\n第1行的第1个数字n表示总人数，第2个数字m表示汽车数量；从第2行开始，接下来的m行表示每辆汽车的司乘人员总人数和人员编号（人员编号是一个固定值，可以对应于我们的身份证号码），每一行的第1个数字k表示该汽车的司乘人员总数，接下来的k个数字表示每一个人的编号。\n\n输出\n需要被隔离的总人数。\n\n样例输入\n100 4\n2 1 2\n5 10 13 11 12 14\n2 0 1\n2 99 2\n\n样例输出\n4\n\n代码：\n\n```java\nimport java.util.Scanner;\n   \npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        while(sc.hasNext()) {\n            int n = sc.nextInt();\n            int m = sc.nextInt();\n            int[] a = new int[n];\n            int[] b = new int[m];\n            int count=1;\n            for(int i=0;i<m;i++) {\n                b[i]=sc.nextInt();\n                int[] c = new int[b[i]];\n                for(int j=0;j<b[i];j++) {\n                    c[j]=sc.nextInt();\n                }\n                for(int j=0;j<b[i];j++) {\n                    if(a[c[j]]==0) {\n                        a[c[j]]=count;\n                    }\n                    else {\n                        for(int k=0;k<b[i];k++) {\n                            a[c[k]]=a[c[j]];\n                        }\n                    }\n                }\n                count++;\n            }\n            count=0;\n            for(int i=0;i<n;i++) {\n                if(a[i]==a[0]) {\n                    count++;\n                }\n            }\n            System.out.println(count);\n        }\n    }\n}\n```\n*PS：同一辆车上的人属于一个集合，如果两辆车上有相同的人，两辆车上的人属于一个集合。*\n\n## 问题 D: 最小生成树（Kruskal）\n题目描述\n编程实现Kruskal算法，求图的最小生成树(MST)的权重。\n\n输入\n每组数据分为两个部分，第一部分为图的点数n，和边数m, \n第二部分为m行，每一行输入三个数字，前两个为两个顶点的编号，第三个为边权重。 \n\n输出\n最小生成树的权重。\n\n样例输入\n3 3\n0 1 10\n0 2 15\n1 2 50\n\n样例输出\n25\n\n代码：\n\n```java\nimport java.util.Scanner;\n   \npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        while(sc.hasNext()) {\n            int n = sc.nextInt();\n            int m = sc.nextInt();\n            int[] a = new int[m];\n            int[] b = new int[m];\n            int[] c = new int[m];\n            int[] d = new int[n];\n            int count=1,sum=0;\n            for(int i=0;i<m;i++) {\n                a[i] = sc.nextInt();\n                b[i] = sc.nextInt();\n                c[i] = sc.nextInt();\n            }\n            for(int i=0;i<m-1;i++) {\n                for(int j=0;j<m-1;j++) {\n                    if(c[j]>c[j+1]) {\n                        int t = a[j];\n                        a[j] = a[j+1];\n                        a[j+1] = t;\n                        t = b[j];\n                        b[j] = b[j+1];\n                        b[j+1] = t;\n                        t = c[j];\n                        c[j] = c[j+1];\n                        c[j+1] = t;\n                    }\n                }\n            }\n            for(int i=0;i<n-1;i++) {\n                if(d[a[i]]==0&&d[b[i]]==0) {\n                    d[a[i]]=count;\n                    d[b[i]]=count;\n                    count++;\n                    sum+=c[i];\n                }\n                else if(d[a[i]]!=0&&d[b[i]]==0) {\n                    d[b[i]]=d[a[i]];\n                    sum+=c[i];\n                }\n                else if(d[a[i]]==0&&d[b[i]]!=0) {\n                    d[a[i]]=d[b[i]];\n                    sum+=c[i];\n                }\n                else if(d[a[i]]!=0&&d[b[i]]!=0&&d[a[i]]!=d[b[i]]) {\n                    for(int j=0;j<n;j++) {\n                        if(d[j]==d[b[i]]) {\n                            d[j]=d[a[i]];\n                        }\n                    }\n                    sum+=c[i];\n                }\n            }\n            System.out.println(sum);\n        }\n    }\n}\n```\n\n## 问题 E: 搭建电路\n题目描述\n明明迷上了一个搭建电路的游戏。\n在游戏中，每次在两个电子元件之间增加一条有效电路（两个元件之间先前没有电路相连）都将获得相应的积分奖励。\n已知电子元件数量n和部分电子元件之间的奖励积分值。如何构建一个有效电路将所有元件全部连接起来，并且可以得到最多的积分奖励。\n\n输入\n每组输入数据包含m+1行。\n第1行输入两个正整数n和m，其中n表示电子元件数量（n<=100），m表示提供了m对电子元件之间的奖励积分值（m<=1000）。两个正整数之间用空格隔开。\n第2行到第m+1行对应m对电子元件及其对应的奖励积分值，每一行包含三个正整数，第1个和第2个整数表示电子元件编号（从1开始），第3个整数表示两个元件之间搭建电路的奖励积分num(num<1e9)。整数之间用空格隔开。\n\n输出\n每组输出占1行，输出一个正整数，即最多可以得到的积分奖励值。如果没有办法把所有元件全部连接起来，则输出“No solution.”。\n\n样例输入\n3 3\n1 2 10\n1 3 20\n2 3 30\n\n样例输出\n50\n\n代码：\n\n```java\nimport java.util.Scanner;\n   \npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        while(sc.hasNext()) {\n            int n = sc.nextInt();\n            int m = sc.nextInt();\n            int[] a = new int[m];\n            int[] b = new int[m];\n            int[] c = new int[m];\n            int[] d = new int[n];//并查集\n            int count=1,t;\n            long sum = 0;\n            for(int i=0;i<m;i++) {\n                a[i] = sc.nextInt()-1;\n                b[i] = sc.nextInt()-1;\n                c[i] = sc.nextInt();\n            }\n            if(m<n-1) {\n                System.out.println(\"No solution.\");\n            }\n            else {\n                for(int i=0;i<m-1;i++) {\n                    for(int j=0;j<m-1;j++) {\n                        if(c[j]<c[j+1]) {\n                            t = a[j];\n                            a[j] = a[j+1];\n                            a[j+1] = t;\n                            t = b[j];\n                            b[j] = b[j+1];\n                            b[j+1] = t;\n                            t = c[j];\n                            c[j] = c[j+1];\n                            c[j+1] = t;\n                        }\n                    }\n                }\n                /*for(int i=0;i<m;i++) {\n                    System.out.println(a[i]+\" \"+b[i]+\" \"+c[i]);\n                }*/\n                for(int i=0;i<m;i++) {\n                    if(d[a[i]]==0&&d[b[i]]==0) {\n                        d[a[i]] = count;\n                        d[b[i]] = count;\n                        count++;\n                        sum+= c[i];\n                    }\n                    else if(d[a[i]]!=0&&d[b[i]]==0) {\n                        d[b[i]] = d[a[i]];\n                        sum+= c[i];\n                    }\n                    else if(d[a[i]]==0&&d[b[i]]!=0) {\n                        d[a[i]] = d[b[i]];\n                        sum+= c[i];\n                    }\n                    else if(d[a[i]]!=0&&d[b[i]]!=0&&d[a[i]]!=d[b[i]]) {\n                        int flag = d[b[i]];\n                        for(int j=0;j<n;j++) {\n                            if(d[j]==flag) {\n                                d[j] = d[a[i]];\n                            }\n                        }\n                        sum+= c[i];\n                    }\n                }\n                int flag=0;\n                for(int i=0;i<n-1;i++) {\n                    if(d[i]!=d[i+1]) {\n                        flag = 1;\n                    }\n                }\n                if(flag==0) {\n                    System.out.println(sum);\n                }\n                else {\n                    System.out.println(\"No solution.\");\n                }\n            }\n        }\n    }\n}\n```\n\n*PS：主要记录结果的变量要用long(:sob::sob::sob:)*\n\n\n## 问题 F: 单源最短路径问题\n题目描述\n编程实现Dijkstra算法，求一个有向加权图中，从源点出发到其他各个顶点的最短路径。\n\n输入\n第1行第1个值表示顶点个数，第2个值表示边个数；第2行开始为边（两个顶点，边的起点和终点）及权重。\n\n输出\n顶点0到每一个顶点的最短路径长度。\n\n样例输入\n5 7\n0 1 10\n0 3 30\n0 4 100\n1 2 50\n2 4 10\n3 2 20\n3 4 60\n\n样例输出\n0 10 50 30 60\n\n代码：\n\n```java\nimport java.util.Scanner;\n \npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int MAX = 10000;\n        while(sc.hasNext()) {\n            int n = sc.nextInt();\n            int m = sc.nextInt();\n            int[][] a = new int[n][n];\n            int[] b = new int[n];//各点距离地点的最近距离\n            int[] c = new int[n];//各点是否被访问\n            for(int i=0;i<n;i++) {\n                for(int j=0;j<n;j++) {\n                    a[i][j] = MAX;\n                }\n            }\n            a[0][0] = 0;\n            c[0] = 1;\n            for(int i=0;i<m;i++) {\n                int j = sc.nextInt();\n                int k = sc.nextInt();\n                a[j][k] = sc.nextInt();\n            }\n            for(int i=0;i<n;i++) {\n                b[i] = a[0][i];\n            }\n            for(int i=0;i<n-1;i++) {\n                int min = MAX;\n                int j = 0;\n                for(int k=0;k<n;k++) {\n                    if(c[k]==0&&b[k]<min) {\n                        min = b[k];\n                        j = k;\n                    }\n                }\n                c[j] = 1;\n                for(int k=0;k<n;k++) {\n                    if(c[k]==0&&(b[j]+a[j][k])<b[k]) {\n                        b[k] = b[j]+a[j][k];\n                    }\n                }\n            }\n            for(int k=0;k<n;k++) {\n                System.out.print(b[k]+\" \");\n            }\n            System.out.println();\n        }\n    }\n}\n```', 'hnucm_oj 算法分析与设计 练习13', 1, 1000, 1084, '2021-05-29 13:57:14', '2021-05-29 13:57:14', NULL, NULL, 1);
INSERT INTO `blog` VALUES (2, 'Oracle 大型数据库技术 2021实验项目八-附加', '19974712583', '', 'Oracle 大型数据库技术 2021实验项目八-附加', 10, 2, 502, '2021-05-30 16:39:01', '2021-05-30 16:39:01', NULL, NULL, 1);
INSERT INTO `blog` VALUES (3, 'hnucm_oj 算法分析与设计 练习14', '19974712583', '', 'hnucm_oj 算法分析与设计 练习14', 2, 0, 0, '2021-06-03 17:32:00', '2021-06-03 17:32:00', NULL, NULL, 1);
INSERT INTO `blog` VALUES (4, 'hnucm_oj 算法分析与设计 练习15', '19974712583', '', 'hnucm_oj 算法分析与设计 练习15', 0, 0, 0, '2021-06-10 19:24:28', '2021-06-10 19:24:28', NULL, NULL, 1);
INSERT INTO `blog` VALUES (5, 'hnucm_oj 算法分析与设计 练习16', '19974712583', '', 'hnucm_oj 算法分析与设计 练习16', 0, 0, 0, '2021-06-24 19:49:06', '2021-06-24 19:49:06', NULL, NULL, 1);
INSERT INTO `blog` VALUES (6, '尚硅谷以太坊区块链学习（1）', '19974712583', '', '尚硅谷以太坊区块链学习（1）', 0, 0, 0, '2022-03-27 20:16:30', '2022-03-27 20:16:30', NULL, NULL, 1);
INSERT INTO `blog` VALUES (7, '尚硅谷以太坊区块链学习（2）', '123', '', '尚硅谷以太坊区块链学习（2）', 0, 0, 0, '2022-03-24 16:09:18', '2022-03-24 16:09:18', NULL, NULL, 1);
INSERT INTO `blog` VALUES (8, '尚硅谷以太坊区块链学习（3）', '19974712583', '', '尚硅谷以太坊区块链学习（3）', 0, 0, 0, '2022-04-04 01:46:44', '2022-04-04 01:46:44', NULL, NULL, 1);
INSERT INTO `blog` VALUES (9, '尚硅谷以太坊区块链学习（4）', '19974712583', '', '尚硅谷以太坊区块链学习（4）', 0, 0, 0, '2022-04-04 21:09:27', '2022-04-04 21:09:27', NULL, NULL, 1);
INSERT INTO `blog` VALUES (10, '尚硅谷以太坊区块链学习之NFT智能合约（6）', '19974712583', '', '尚硅谷以太坊区块链学习之NFT智能合约（6）', 0, 0, 0, '2022-04-16 23:16:42', '2022-04-16 23:16:42', NULL, NULL, 1);
INSERT INTO `blog` VALUES (11, '尚硅谷以太坊区块链学习之Web3j（7）', '19974712583', '', '尚硅谷以太坊区块链学习之Web3j（7）', 3, 0, 1, '2022-04-17 21:36:34', '2022-04-17 21:36:34', NULL, NULL, 1);
INSERT INTO `blog` VALUES (12, '软件测试之接口测试实验', '19974712583', '', '软件测试之接口测试实验', 0, 0, 1, '2022-05-13 20:56:40', '2022-05-13 20:56:40', NULL, NULL, 1);
INSERT INTO `blog` VALUES (13, '推荐20个开源的不错前端低代码项目', '19974712583', '', '近几年，在技术领域低代码是比较热门的话题，比如阿里云推出了易搭，通过简单的拖拽、配置，即可完成业务应用的搭建，腾讯云则是推出了微搭，通过行业化模板、拖放式组件和可视化配置快速构建多端应用。低代码是基于可视化和模型驱动理念，结合云原生与多端体验技术，它能够在多数业务场景下实现大幅度的提效降本，为专业开发者提供了一种全新的高生产力开发范式。下面就来分享几个值得学习和使用的低代码开源项目，更深入地了解什么是低代码。', 0, 0, 1001, '2022-11-15 09:22:29', '2022-11-15 09:22:29', NULL, '/blog/cover/e278fe2e2d3748d4a2d28838a1b13ace.png', 1);
INSERT INTO `blog` VALUES (14, 'Web大学生网页作业成品——环保垃圾分类网站设计与实现(HTML+CSS+JavaScript) web前端开发技术 web课程设计 网页规划与设计', '19974712583', '', '环境保护、 保护地球、 校园环保、垃圾分类、绿色家园、等网站的设计与制作。总结了一些学生网页制作的经验：一般的网页需要融入以下知识点：div+css布局、浮动、定位、高级css、表格、表单及验证、js轮播图、音频 视频 Flash的应用、ul li、下拉导航栏、鼠标划过效果等知识点，网页的风格主题也很全面：如爱好、风景、校园、美食、动漫、游戏、咖啡、音乐、家乡、电影、名人、商城以及个人主页等主题，学生、新手可参考下方页面的布局和设计和HTML源码（有用点赞） 一套A+的网', 0, 0, 1002, '2022-11-14 05:46:42', '2022-11-14 05:46:42', NULL, '/blog/cover/1668150833681.png', 1);
INSERT INTO `blog` VALUES (15, '公司缺人自己搞了vue又搞koa，熬夜把架子搭起来', '19974712583', '', '如果有一天，人手紧缺，自己搞了前端还要搞服务端，今天我们把这个项目架子搭起来，让前端同学也可以轻松全栈开火。', 0, 0, 1003, '2022-11-08 13:18:52', '2022-11-08 13:18:52', NULL, '/blog/cover/919bb405dacb43bca5288423bfa643cc.png', 1);
INSERT INTO `blog` VALUES (16, '优雅写代码的45个小技巧', '19974712583', '', '比如在项目中不同的类型的业务可能需要上传各种各样的附件，此时就可以定义好不同的一个附件的枚举，来区分不同业务的附件。不要在代码中直接写死，不定义枚举，代码阅读起来非常困难，直接看到数字都是懵逼的。。', 0, 0, 1004, '2022-11-06 03:45:00', '2022-11-06 03:45:00', NULL, '/blog/cover/8046ea57db754f67ad44c57060ab0755.png', 1);
INSERT INTO `blog` VALUES (17, 'HTML小游戏6 —— 《高达战争》横版射击游戏（附完整源码）', '19974712583', '', '本节教程我会带大家使用 HTML 、CSS和 JS 来制作一个本节示例将会实现如下所示的效果：源码也可在文末进行获取。', 0, 0, 1006, '2022-11-11 13:29:26', '2022-11-11 13:29:26', NULL, '/blog/cover/e8b5a9e278ba40afa639a4f48adb68c0.png', 1);
INSERT INTO `blog` VALUES (18, '问题 B: 相聚HNUCM校园食堂', '123456', '', '问题 B: 相聚HNUCM校园食堂', 0, 0, 0, '2020-05-27 15:45:47', '2020-05-27 15:45:47', NULL, NULL, 1);
INSERT INTO `blog` VALUES (19, '【无主题】（模板）', '', '# 前言\r\n\r\n[[TOC]]\r\n\r\n`提示：这里可以添加本文要记录的大概内容：`\r\n\r\n例如：随着人工智能的不断发展，机器学习这门技术也越来越重要，很多人都开启了学习机器学习，本文就介绍了机器学习的基础内容。\r\n\r\n---\r\n\r\n`提示：以下是本篇文章正文内容，下面案例可供参考`\r\n\r\n## 一、pandas是什么？\r\n示例：pandas 是基于NumPy 的一种工具，该工具是为了解决数据分析任务而创建的。\r\n\r\n## 二、使用步骤\r\n### 1.引入库\r\n代码如下（示例）：\r\n\r\n```c\r\nimport numpy as np\r\nimport pandas as pd\r\nimport matplotlib.pyplot as plt\r\nimport seaborn as sns\r\nimport warnings\r\nwarnings.filterwarnings(\'ignore\')\r\nimport  ssl\r\nssl._create_default_https_context = ssl._create_unverified_context\r\n```\r\n\r\n### 2.读入数据\r\n代码如下（示例）：\r\n```c\r\ndata = pd.read_csv(\r\n    \'https://labfile.oss.aliyuncs.com/courses/1283/adult.data.csv\')\r\nprint(data.head())\r\n```\r\n该处使用的url网络请求的数据。\r\n\r\n---\r\n\r\n## 总结\r\n提示：这里对文章进行总结：\r\n例如：以上就是今天要讲的内容，本文仅仅简单介绍了pandas的使用，而pandas提供了大量能使我们快速便捷地处理数据的函数和方法。', '【无主题】（模板）', 0, 0, 0, '', '', '', NULL, 0);

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `from` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `to` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sendTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (3, '19974712583', '123', '123', '2022-11-23 01:16');
INSERT INTO `chat` VALUES (4, '19974712583', '123456', '123', '2023-3-4 21:05');
INSERT INTO `chat` VALUES (5, '19974712583', '123', '123', '2023-3-6 19:20');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `collectId` int(12) NULL DEFAULT NULL COMMENT '收藏id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收藏的类型',
  `collectTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES (1, '123456', 12, 'blog', NULL);
INSERT INTO `collect` VALUES (2, '123456', 12, 'blog', NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被评论账号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论内容',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论类型（博文、动态、评论）',
  `commentAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论账号',
  `commentContentId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论事务（id）',
  `commentTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '评论时间',
  `isVisible` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被评论账号是否还可见（1代表可见，0代表不可见）',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '19974712583', '写的很详细', 'blog', '123456', '1', '2022-06-01 12:00:00', '1');
INSERT INTO `comment` VALUES (2, '19974712583', '大佬，这两个类能给下么', 'blog', '123456', '11', '2022-06-01 12:00:00', '1');
INSERT INTO `comment` VALUES (3, '123456', '666', 'comment', '19974712583', '2', '2022-06-01 12:00:00', '1');
INSERT INTO `comment` VALUES (4, '19974712583', '1', 'comment', '123456', '3', '2022-06-01 12:00:00', '1');
INSERT INTO `comment` VALUES (5, '123456', '怎么事？', 'blog', '123456', '18', '2022-06-01 12:00:00', '1');
INSERT INTO `comment` VALUES (6, '19974712583', '就是利用贪心的思想，要想配对最多', 'comment', '123456', '5', '2022-06-01 13:00:00', '1');
INSERT INTO `comment` VALUES (7, '19974712583', '真不错', 'blog', '123', '2', '2022-06-01 14:00:00', '1');
INSERT INTO `comment` VALUES (8, '19974712583', '博主文章很详细，有点问题想交流下，方便留个威信嘛～', 'blog', '123', '12', '2022-06-01 14:00:00', '1');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '拥有者',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '铸造者',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '铸造时间',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '介绍',
  `information` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信息',
  `isSale` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否上架',
  `saleTime` datetime(0) NULL DEFAULT NULL COMMENT '上架时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------

-- ----------------------------
-- Table structure for concern
-- ----------------------------
DROP TABLE IF EXISTS `concern`;
CREATE TABLE `concern`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `concernAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被关注账号',
  `concernLocation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关注位置（通过什么关注的）',
  `concernTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of concern
-- ----------------------------
INSERT INTO `concern` VALUES (1, '123456', '19974712583', NULL, '2022-04-04 01:46:44');
INSERT INTO `concern` VALUES (2, '123456', '123', NULL, '2022-04-04 01:46:44');

-- ----------------------------
-- Table structure for dynamics
-- ----------------------------
DROP TABLE IF EXISTS `dynamics`;
CREATE TABLE `dynamics`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `createAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '发布动态的账号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '动态内容',
  `thumbsUpCount` int(12) NULL DEFAULT NULL COMMENT '点赞数',
  `thumbsDownCount` int(12) NULL DEFAULT NULL COMMENT '踩的次数',
  `visits` int(12) NULL DEFAULT NULL COMMENT '查看次数',
  `createTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发布时间',
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '如果有图片，就需要图片路径',
  PRIMARY KEY (`id`, `createAccount`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamics
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`, `roleName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '个人简介',
  `sex` char(3) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '19974712583', 'TA很懒，还没有添加简介', NULL, NULL);
INSERT INTO `userinfo` VALUES (2, '123456', 'TA很懒，还没有添加简介', NULL, NULL);
INSERT INTO `userinfo` VALUES (3, '123', 'TA很懒，还没有添加简介', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
