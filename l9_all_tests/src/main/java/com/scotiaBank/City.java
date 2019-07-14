package com.scotiaBank;

import java.util.HashSet;
import java.util.Objects;

public class City {

    static class Dest {
        int from;
        int to;

        public Dest(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dest dest = (Dest) o;
            return from == dest.from &&
                    to == dest.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    //public int solution(int[] A) {
    public static void main(String[] args) {
        int[] A = {1, -3, 1, 1, 1};
        HashSet<Dest> cache = new HashSet<>();
        int max = Integer.MIN_VALUE;
        for (int out = 0; out != A.length; out++) {
            for (int in = 0; in != A.length; in++) {
                Dest forward = new Dest(out, in);
                Dest backward = new Dest(in, out);
                if (!cache.contains(forward)) {
                    System.out.println("out= " + out + ", in= " + in);
                    int diff = A[out] + A[in] + Math.abs(out - in);
                    if (diff > max) {
                        max = diff;
                    }
                    cache.add(forward);
                    cache.add(backward);
                }
            }
        }
        System.out.println(max);
       // return max;
    }
}
