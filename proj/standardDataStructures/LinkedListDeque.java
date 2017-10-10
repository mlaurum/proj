public class LinkedListDeque <Item>{
	private class Node {
      private Node prev;
		private Item item;
		private Node next;

		private Node(Node previous, Item item, Node next){
			prev = previous;
         this.item = item;
			this.next = next;
		}
	}


	private int size;
	
	private Node sentinel;


	/* Empty constructor*/
	public LinkedListDeque(){
	sentinel = new Node(null, null, null);
   sentinel.next = sentinel;
   sentinel.prev = sentinel;
	size=0;
	}

	/*public LinkedListDeque(Item x){
	sentinel = new Node(sentinel, null, null);
	Node one = new Node(sentinel , x , sentinel);
	sentinel.next = one;
	sentinel.prev = one;
	size=1;
	}*/

	public void addFirst(Item item){
	Node front = new Node(sentinel, item, sentinel.next);
	sentinel.next.prev=front;
	sentinel.next = front;
	size += 1;
	}
   
	public void addLast(Item item){
	Node back = new Node(sentinel.prev, item, sentinel);
	sentinel.prev.next = back;
	sentinel.prev = back;
	size += 1;
	}


   public void printDeque(){
	Node i = sentinel;
      while(i.next.item != null){
         i=i.next;
			if(i.next.item == null){
				System.out.println(i.item);
            break;
			}
         
			System.out.print(i.item + ", ");
         
		}
	}

   public boolean isEmpty(){
		if (size == 0)
			{return true;}
		return false;
	}

	public int size(){
		return size;
	}

	public Item get(int index){
		//change to size implementation...mebe
		if (index>=size){
			return null;
		}
		Node p = sentinel;
		for(int i = 0; i<=index; i++){
			p = p.next;
		}
		return p.item;
	}

	public Item removeFirst(){
		//check size zero list
		Node toDel = sentinel.next;
		sentinel.next = toDel.next;
		toDel.next.prev = sentinel;
		size-=1;
		return toDel.item;

	}

	public Item removeLast(){
		//remeber to check if it is a size zero list
		Node toDel = sentinel.prev;
		sentinel.prev = toDel.prev;
		toDel.prev.next = sentinel;
		size-=1;
		return toDel.item;
	}

	public Item getRecursive(int index){
		if (index>=size){
			return null;
		}
		return helperR(index, sentinel.next);
		}

	private Item helperR(int index, Node track){
		if(index == 0){
			return track.item;
		}
		return helperR(index-1, track.next);
	}
  
    /*public static void main(String[] args){
   LinkedListDeque test = new LinkedListDeque();
   test.addFirst(0);
   test.addLast(1);
   test.addLast(2);
   test.addLast(3);
   test.addLast(4);
   test.printDeque();
   System.out.println(test.getRecursive(2));
   System.out.println(test.get(4));
   test.removeLast();
   System.out.println(test.get(7));
   System.out.println(test.getRecursive(7));
   }*/

}