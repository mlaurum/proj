public class ArrayDeque<Item>{
  private Item[] items;
  private int size;
  private int frontAdd;
  private int lastAdd;
   private int rFactor;

  public ArrayDeque(){
  items = (Item[]) new Object[8];
  size=0;
   frontAdd=0;
  lastAdd=1;
    rFactor =2;
  }
   
   private void resize(int capacity){
    Item[] holdr = (Item[]) new Object[capacity];
    if((frontAdd+1)%items.length<(lastAdd-1)%items.length){

      System.arraycopy(items,(frontAdd +1)%items.length, holdr, 0,size);
    
    }else{
  
  //size should be lenght
    System.arraycopy(items,(frontAdd +1) % size, holdr, 0, (items.length)-(frontAdd+1));
    System.arraycopy(items, 0,holdr, size-(frontAdd +1),frontAdd+1 );
    
    }
    
    items = holdr;
    frontAdd = items.length-1;
    lastAdd = size;
   }
   


  public void addFirst(Item Item){
    
      if (size<items.length){
      items[frontAdd]=Item;
      if (frontAdd == 0){
        frontAdd= frontAdd - 1 +items.length ;
        size++;
      }else{
        frontAdd --;
        size++;
      }

      }
      else{
      resize(size*rFactor);
      addFirst(Item);
      
      }
  }
   
   public void addLast(Item Item){
    
  if (size<items.length){
      items[lastAdd]=Item;
      if (lastAdd == items.length-1){
        lastAdd= lastAdd + 1 -items.length ;
        size++;
      }else{
        lastAdd ++;
        size++;
      }

      }
      else{
      resize(size*rFactor);
      addLast(Item);
      
      }
  }

  public Item removeFirst(){
    
    //TODO: check if array size is zero and resize if too many nulls
    if(frontAdd == items.length-1){
      frontAdd=frontAdd+1-items.length;
    }else{
       frontAdd =frontAdd+1;
    }
    Item deItem = items[frontAdd];
    items[frontAdd]=null;
    size --;
    if (size <= items.length/4 && size>=16){
      resize(items.length/2);
    }
    
    return deItem;

  }

  public Item removeLast(){
    //TODO: look at removeFirst;
    if (size <= items.length/4 && size>=16){
      resize(items.length/2);
    }
    if(lastAdd == 0){
      lastAdd=lastAdd-1+items.length;
    }else{
       lastAdd =lastAdd-1;
    }
    Item deItem = items[lastAdd];
    items[lastAdd]=null;
    size --;
    if (size <= items.length/4 && size>=16){
      resize(items.length/2);
    }
    return deItem;
  }

   public void printDeque(){
      //some mod op.
      int ind = frontAdd+1;
    for(int i = 0; i<size; i++){
      if (ind == items.length){
        ind = ind - items.length;
      }
      if(ind == lastAdd-1){
        System.out.println(items[ind]);
            break;
      }
         
      System.out.print(items[ind]+ " ");
         ind++;
         
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
    int startIndex = frontAdd +1;
    int ind= (startIndex+index) % (items.length);
    return items[ind];
  }


  /*public static void main(String args[]){
   ArrayDeque test = new ArrayDeque();
    test.addLast(0);
              
         
         test.addLast(3);
         test.addLast(4);
         test.addLast(5);
         test.addLast(6);
         test.addLast(7);
         test.addLast(8);
         
         test.addLast(10);
         test.addLast(11);
         test.printDeque();
      
   }*/

  
}