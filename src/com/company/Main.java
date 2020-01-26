package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }
}

class ExamRoom {
    int N;
    TreeSet<Integer> students;

    public ExamRoom(int N) {
        this.N = N;
        students = new TreeSet();
    }

    public int seat() {
        //Let's determine student, the position of the next
        //student to sit down.
        int student = 0;
        if (students.size() > 0) {
            //Tenatively, dist is the distance to the closest student,
            //which is achieved by sitting in the position 'student'.
            //We start by considering the left-most seat.
            int dist = students.first();
            Integer prev = null;
            for (Integer s : students) {
                if (prev != null) {
                    //For each pair of adjacent students in positions (prev, s),
                    //d is the distance to the closest student;
                    //achieved at position prev + d.
                    int d = (s - prev) / 2;
                    if (d > dist) {
                        dist = d;
                        student = prev + d;
                    }
                }
                prev = s;
            }

            //Considering the right-most seat.
            if (N - 1 - students.last() > dist)
                student = N - 1;
        }

        //Add the student to our sorted TreeSet of positions.
        students.add(student);
        return student;
    }

    public void leave(int p) {
        students.remove(p);
    }
}

class ExamRoom {
    int N;
    ArrayList<Integer> L = new ArrayList<>();

    public ExamRoom(int n) {
        N = n;
    }

    public int seat() {
        if (L.size() == 0) {
            L.add(0);
            return 0;
        }
        int d = Math.max(L.get(0), N - 1 - L.get(L.size() - 1));
        for (int i = 0; i < L.size() - 1; ++i)
            d = Math.max(d, (L.get(i + 1) - L.get(i)) / 2);
        if (L.get(0) == d) {
            L.add(0, 0);
            return 0;
        }
        for (int i = 0; i < L.size() - 1; ++i)
            if ((L.get(i + 1) - L.get(i)) / 2 == d) {
                L.add(i + 1, (L.get(i + 1) + L.get(i)) / 2);
                return L.get(i + 1);
            }
        L.add(N - 1);
        return N - 1;
    }

    public void leave(int p) {
        for (int i = 0; i < L.size(); ++i)
            if (L.get(i) == p)
                L.remove(i);
    }
}
