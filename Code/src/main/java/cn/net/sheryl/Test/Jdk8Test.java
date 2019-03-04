package cn.net.sheryl.Test;

import java.util.Arrays;

public class Jdk8Test {
	public static void main(String[] args) {
		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
	}
}
