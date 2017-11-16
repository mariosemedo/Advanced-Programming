package labs.lab1.student;

import java.util.Comparator;

import static java.util.Arrays.sort;

public class Student implements Comparable<Student>{
	public int id; // unique identifier
	public String name;
	public String scheme;

	public static final Student[] sampleStudents = {
			new Student(56781234, "Alice", "Law"),
			new Student(87654321, "Bob", "Astronomy"),
			new Student(12345678, "Carol", "Computer Science"),
			new Student(31415926, "Bob", "Philosophy")};

	public Student(int id, String name, String scheme) {
		this.id = id;
		this.name = name;
		this.scheme = scheme;
	}

	@Override
	public String toString() {
		return ("<" + name + ", " + scheme + ", " + id + ">");
	}

	public static void main(String[] args) {
		for (Student s : sampleStudents)
			System.out.println(s);

		System.out.println("\n");
		testComparable(); 
		testComparator();
		for (Student s : sampleStudents)
			System.out.println(s);
	}

	public static void testComparable() {
		// please insert test code
		sort(sampleStudents);
	}

	public static void testComparator() {
		// please insert test code
		sort(sampleStudents, compareByNameFirst );

	}

	@Override
	public int compareTo(Student o) {
		if(id > o.id) return 1;
		if(id ==o.id) return 0;
		return -1;
	}
/*
	static Comparator<Student> compareByNameFirst = new Comparator<Student>() {
		@Override
		public int compare(Student o1, Student o2) {
			return o1.name.compareTo(o2.name);
		}
	}; */

	/*Comparator<Student> compareByNameFirst = ((o1, o2) -> o1.name.compareTo(o2.name));*/

	static Comparator<Student> compareByNameFirst = Comparator.comparing(o -> o.name);

}
