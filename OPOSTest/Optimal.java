package org.unibl.etf.OPOSTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Optimal {
    private static class Page {
        private int pageNo;
        private int howFar;
        private int id;
        private static int idMaker;

        public Page(int pageNo, int howFar) {
            this.pageNo = pageNo;
            this.howFar = howFar;
            id = ++idMaker;
        }

        public int getPageNo() {
            return pageNo;
        }

        public int getHowFar() {
            return howFar;
        }

        public int getId() {
            return id;
        }

        public void setHowFar(int howFar) {
            this.howFar = howFar;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }
    }

    private static ArrayList<Page> loaded = new ArrayList<>();
    private static int pfCounter, curr;

    private static void loadPages(String[] pages) {
        for (String page : pages) {
            boolean contains = loaded.stream().anyMatch(p -> p.getPageNo() == Integer.valueOf(page));
            if (contains)
                printPages(false);
            else if (loaded.size() < 3) {
                ++pfCounter;
                loaded.add(new Page(Integer.valueOf(page), 0));
                printPages(true);
            } else {
                ++pfCounter;
                changePage(Integer.valueOf(page), pages);
                printPages(true);
            }
            ++curr;
        }
    }

    private static void changePage(Integer page, String[] pages) {
        TreeSet<Page> tmp = new TreeSet<>(Comparator.comparingLong(Page::getHowFar).reversed().thenComparing(Page::getId));
        for (Page p : loaded) {
            int howFar = 0;
            boolean flag = true;
            for (int i = curr; i < pages.length; ++i) {
                boolean contains = tmp.stream().anyMatch(pr -> pr.getPageNo() == p.getPageNo());
                if (!contains && p.getPageNo() == Integer.valueOf(pages[i])) {
                    tmp.add(new Page(p.getPageNo(), howFar));
                    flag = false;
                } else
                    ++howFar;
            }
            if (flag)
                tmp.add(new Page(p.getPageNo(), Integer.MAX_VALUE));
        }
        if (tmp.isEmpty())
            return;
        Page toChange = tmp.pollFirst();
        loaded.forEach(c -> {
            if (c.getPageNo() == toChange.getPageNo())
                c.setPageNo(page);
        });
    }

    private static void printPages(boolean pfOccur) {
        for (Page page : loaded)
            System.out.format("|| %-2d ", page.getPageNo());
        if (pfOccur)
            System.out.println("||  *");
        else
            System.out.println("||  /");
    }

    // 7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1 ( ulazna sekvenca za 3 okvira - primjer sa prezentacije )
    // 1,4,6,3,4,6,4,2,1,4,5,6,4,5,6
    // 1,2,3,4,3,1,5,7,2,1,5,3,7,4,6,2,7
    // 1,2,3,4,3,1,5,7,2,1,5,3,7,4,6,2,7,2,4,6
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter pages (1,2,3,4,5,...) : ");
        String[] pages = scanner.next().split(",");
        loadPages(pages);
        System.out.println("Total page faults: " + pfCounter);
    }
}
