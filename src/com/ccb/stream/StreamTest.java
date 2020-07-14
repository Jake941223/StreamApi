package com.ccb.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * StreamApi
 * @author jesse941223
 *
 */
public class StreamTest {

	 static void gen1(){
		//第一种创建Stream通过数组生成stream
		String [] strs = {"a","b","c","d"};
		Stream<String> str1 = Stream.of(strs);
		str1.forEach(System.out::println);//第一种循环遍历
		//第二种增强型for循环
		for (String string : strs) {
			System.out.println(string);
			
		}
				

	}
	static void gen2() {
		//第二种创建stream
		List<String> list = Arrays.asList("1","2","3","4");
		list.stream().forEach(System.out::println);//直接遍历输出
		
	}
	static void gen3() {
		//第三种创建stream generate
		Stream<Integer> generate = Stream.generate(()->1);
		//结果：不停循环打印1
		//generate.forEach(System.out::println);
		//停止的话用limit限制打印个数
		generate.limit(10).forEach(System.out::println);
	}
	static void gen4() {
		//第四种生成Stream  
		Stream<Integer> iterate = Stream.iterate(1, x->x+1);
		iterate.limit(10).forEach(System.out::println);
	}
	//第五种 其他API生成stream
	static void gen5() {
		String string = "abcdefg";
		IntStream stream = string.chars();
		//stream.forEach(System.out::println);
		OfInt iterator = stream.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
	}
	
	public static void main(String[] args) {
		//gen1();
		//gen2();
		//gen3();
		//gen4();
		//gen5();
		
		//中间操作 如果调用方法之后返回一个stream对象意味着是一个中间操作
		//filter 过滤操作 
		Arrays.asList(1,2,3,4,5).stream().filter(x->x%2==0).forEach(System.out::println);
		//求出结果集中所有偶数的和
		//符合条件的个数
		long count = Arrays.asList(1,2,3,4,5,6,7,8,9).stream().filter(x->x%2==0).count();
		System.out.println(count);
		//利用stream的子类intStream的sum方法
		System.out.println("-----------------------");
		 int sum = Arrays.asList(1,2,3,4,5,6,7,8,9).stream().filter(x->x%2==0).mapToInt(x->x).sum();
		 System.out.println(sum);
		 //求集合中的最大值
		 List<Integer> list = Arrays.asList(1,2,3,4,33,5,6,-1);
		 Optional<Integer> max = list.stream().max((x,y)->x-y);
		 System.out.println(max.get());
		 //求集合的最小值
		 Optional<Integer> min = list.stream().min((x,y)->x-y);
		 OptionalDouble average = list.stream().filter(x->x%2==0).mapToInt(x->x).average();
		 System.out.println("平均值:"+average.getAsDouble());
		 System.out.println(min.get());
		 //不使用max、min取得最大值、最小值
		 System.out.println("================");
		 Optional<Integer> findFirst = list.stream().sorted().findFirst();
		 //自然排序，取得最小值
		 System.out.println(findFirst.get());
		 Optional<Integer> min2= list.stream().sorted((a,b)->b-a).findFirst();
	
		 System.out.println(min2.get());
		 System.out.println("------------------------------------");
		 Arrays.asList("java","c#","python","scala").stream().sorted((a,b)->a.length()-b.length()).forEach(System.out::println);
		 //收集器collect  返回一个集合
		 List<Integer> collect = list.stream().filter(x->x%2==0).collect(Collectors.toList());
		 collect.forEach(System.out::println);
		 System.out.println("去重操作==========");
		 //去重操作
		 Arrays.asList(1,2,3,3,3,4,5,2).stream().distinct().forEach(System.out::println);
		 //利用collect中的set集合去重
		 System.out.println("利用set集合去重");
		 Arrays.asList(1,2,3,3,3,4,5,2).stream().collect(Collectors.toSet())
		 .forEach(System.out::println);
		 //打印20-30的数据集合
		 Stream.iterate(1, x->x+1).limit(50).skip(20).limit(10).forEach(System.out::println);
		 //将字符串数组转换成int数组 的stream然后进行计算
		 String str = "11,22,33,44,55";
		 System.out.println(Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum());
		 System.out.println(Stream.of(str.split(",")).map(x->Integer.valueOf(x)).mapToInt(x->x).sum());
		//简便写法
		 System.out.println("简便写法");
		 System.out.println(Stream.of(str.split(",")).mapToInt(Integer::valueOf).sum());
		 System.out.println(Stream.of(str.split(",")).map(Integer::valueOf).mapToInt(x->x).sum());
		 // 创建一组自定义对象
		 String string  ="java,scala,python";
		 //构造器引用 lambda表达式
		 Stream.of(string.split(",")).map(x->new Persion(x)).forEach(System.out::println);
		 System.out.println("构造方法引用");
		 Stream.of(string.split(",")).map(Persion::new).forEach(System.out::println);
		 //静态方法引用
		 System.out.println("静态方法引用");
		 Stream.of(string.split(",")).map(x->Persion.build(x)).forEach(System.out::println);
		 Stream.of(string.split(",")).map(Persion::build).forEach(System.out::println);
		   
	}

}
