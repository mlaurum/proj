import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDeque1B{
	
	@Test
	public void fillEmptyFill(){

		for(int k=0 ; k<10; k ++){
		StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> act = new ArrayDequeSolution<Integer>();
		FailureSequence fs = new FailureSequence();
		int length = StdRandom.uniform(100);
		int ind = 0;
		for (int i = 0; i<length; i++){
			int x = StdRandom.uniform(100);
			if(StdRandom.bernoulli(0.5)){
				DequeOperation dequeOp1 = new DequeOperation("addLast", x);
				fs.addOperation(dequeOp1);
				test.addLast(x);
				act.addLast(x);
				int th= test.get(ind);
				int actth = act.get(ind);
			assertEquals(fs.toString(),th ,actth);
				ind ++;
			}else{
				DequeOperation dequeOp2 = new DequeOperation("addFirst", x);
				fs.addOperation(dequeOp2);
				test.addFirst(x);
				act.addFirst(x);
				int th= test.get(0);
				int actth = act.get(0);
			assertEquals(fs.toString(),th ,actth);
				ind ++;
			}
			
		}
		DequeOperation dequeOp3 = new DequeOperation("size");
		fs.addOperation(dequeOp3);
		assertEquals(fs.toString(), test.size(), length);
		
			
		for (int i = length-1; i>=0; i--){
			if (ind <0){break;}
			if(StdRandom.bernoulli(0.5)){
				
				DequeOperation dequeOp4 = new DequeOperation("removeLast");
				fs.addOperation(dequeOp4);
				test.removeLast();
				act.removeLast();
				
				

			}else{
				
				DequeOperation dequeOp5 = new DequeOperation("removeFirst");
				fs.addOperation(dequeOp5);
				test.removeFirst();
				act.removeFirst();
				
				
			}

		
		}
		

		DequeOperation dequeOp8 = new DequeOperation("isEmpty");
		fs.addOperation(dequeOp8);
		assertEquals(fs.toString(), act.isEmpty(), test.isEmpty());

		
	
		}
	}


	@Test
	public void testGet(){
		StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
		int length = StdRandom.uniform(100000);
		int tryF = StdRandom.uniform(100000);
		int tryL = StdRandom.uniform(100000);
		int expected = 0;
		int got = 0;
		for (int i = 0; i<length; i++){
			if(i<length-1){
				if(StdRandom.bernoulli(0.5)){
				test.addLast(StdRandom.uniform(1000));
				
			    }else{
			    test.addFirst(StdRandom.uniform(1000));
				
			    }


			}else{
				if(StdRandom.bernoulli(0.5)){
				test.addLast(tryL);
				expected =tryL;
				got=test.get(length-1);
			    }else{
			    test.addFirst(tryF);
				expected = tryF;
				got=test.get(0);
			    }
			}
			
		}
		
		System.out.println("expected: " + expected + " got: "+ got);
		assertEquals( expected, got);
		

	}

	@Test
	public void testEdge(){
		StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
		test.addLast(1);
		int x = test.size();
		System.out.print(x);
		assertEquals(null, test.get(x+100));

	}

	@Test
	public void unit(){
		StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
		ArrayDequeSolution<Integer> act = new ArrayDequeSolution<Integer>();
		FailureSequence fs = new FailureSequence();
		

		for(int i = 0; i<1000; i++){
			if(StdRandom.bernoulli(0.1)){
				
				DequeOperation dequeOp1 = new DequeOperation("size");
				fs.addOperation(dequeOp1);

				assertEquals( fs.toString(), act.isEmpty(),test.isEmpty());

				
			}
			else if (StdRandom.bernoulli(0.2)) {
				int addNum = StdRandom.uniform(100000);
				test.addFirst(addNum);
				act.addFirst(addNum);
				DequeOperation dequeOp3 = new DequeOperation("addFirst", addNum);
				fs.addOperation(dequeOp3);
				
				

			}
			else if (StdRandom.bernoulli(0.2)) {
				int addNum = StdRandom.uniform(100000);
				
				test.addLast(addNum);
				act.addLast(addNum);
				DequeOperation dequeOp4 = new DequeOperation("addLast", addNum);
				fs.addOperation(dequeOp4);
				
				
			}
			else if(StdRandom.bernoulli(0.25)){
				DequeOperation dequeOp6 = new DequeOperation("removeLast");
				fs.addOperation(dequeOp6);
				test.removeLast();
				act.removeLast();

				
				
				

			}else if (StdRandom.bernoulli(0.25)) 
			{
				
				DequeOperation dequeOp5 = new DequeOperation("removeFirst");
				fs.addOperation(dequeOp5);
				test.removeFirst();
				act.removeFirst();
				
				
			}

		}

		for(int i = 0; i<1000; i++){
			DequeOperation dequeOp7 = new DequeOperation("get", i);
			fs.addOperation(dequeOp7);
			int aGet = act.get(i);
			int get = test.get(i);
			assertEquals( fs.toString(), aGet, get); 
		}

	}

	public static void main(String[] args) {
	jh61b.junit.TestRunner.runTests("all", TestArrayDeque1B.class);
	}
}