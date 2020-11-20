/* DSA 20-21
 * Heap
 */

import java.util.*;

public class Heap<C extends Comparable>{
    private ArrayList<C> heap;

    private int left(int pos) { return 2 * pos + 1; }
    private int right(int pos) { return 2 * pos + 2; }
    private int parent(int pos) { return (pos - 1) / 2; }

    private boolean isLessThan(int pos1, int pos2){
	return heap.get(pos1).compareTo(heap.get(pos2)) < 0;
    }
    
    public Heap(){
	heap = new ArrayList<C>();
    }

    public C peek() {
	if (isEmpty()) return null;
	return heap.get(0);
    }
    public int size() { return heap.size(); }
    
    public boolean isEmpty() { return heap.size() == 0; }

    public void insert(C elt){
	// put the new element at the end of the heap
	// reheap it up to the correct spot
	heap.add(elt);
	//reheaping
	int currPos = heap.size() - 1;
	//while greater than parent, swap them
	while (parent(currPos) >= 0 && isLessThan(parent(currPos),currPos)){
	    //swap now!
	    C temp = heap.get(currPos);
	    //set currPos to the value of parent
	    heap.set(currPos, heap.get(parent(currPos)));
	    heap.set(parent(currPos), temp);
	    currPos = parent(currPos);
	}
    }

    private boolean hasTwoChildren(int pos){
	return right(pos) < heap.size();
    }
    private boolean hasChildren(int pos){
	return left(pos) < heap.size();
    }

    private int maxChild(int pos){ //returns position of maximum child
	if (!hasChildren(pos)) return -1;
	if (hasTwoChildren(pos) && isLessThan(left(pos), right(pos)))
	    return right(pos);
	return left(pos);
    }
    
    public C remove(){
	if (isEmpty()) return null;
	C toReturn = heap.get(0);
	heap.set(0, heap.get(heap.size() - 1));
	heap.remove(heap.size() - 1);

	int currPos = 0;
	// while currPos has children that are greater than it, swap
	while (hasChildren(currPos) && isLessThan(currPos, maxChild(currPos))){
	    // swap with maxChild
	    C temp = heap.get(currPos);
	    int child = maxChild(currPos);
	    heap.set(currPos, heap.get(child));
	    heap.set(child, temp);
	    currPos = child;
	}
	return toReturn;

    }

    private void print(){ System.out.println(heap); }

    public static void main(String[] args){
	Heap<Integer> myHeap = new Heap<Integer>();
	for (int i = 10; i > 0; i--){
	    myHeap.insert(i);
	    myHeap.print();
	}
	myHeap.print();
	myHeap.remove();
	myHeap.print();


    }
}
