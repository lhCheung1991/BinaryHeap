import java.util.Comparator;

public class BinaryHeap <T> {
	
	private static final int DEFAULT_CAPACITY = 100;
	private Comparator<T> comp = null;
	private int size;    // size specific position of  the last element in array
	private int capacity = 0;    // actual capacity
	private T[] array;
	
	
	public BinaryHeap(Comparator<T> _comp){
		this(_comp, DEFAULT_CAPACITY);
	}
	
	public BinaryHeap(Comparator<T> _comp, int _capacity){
		comp = _comp;
		size = 0;
		capacity = _capacity;
		array = (T[])new Object[capacity];
	}
	
	public boolean isFull(){
		return size == (capacity - 1);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public void insert(T elem){
		
		if (isFull())
			throw new RuntimeException("The binary heap is Full.");
		
		int child_ptr = ++size;    // the child node pointer
									// maintain the position of new node
		
		while (child_ptr > 1){    // only root 1 has no father
			
			if (comp.compare(elem, array[child_ptr / 2]) < 0){    // compare with father
				array[child_ptr] = array[child_ptr / 2];
				child_ptr /= 2;    // flow up
			}else{
				break;
			}
			
		}
		
		array[child_ptr] = elem;
	}
	
	public T getTop(){
		
		if (isEmpty())
			return null;
		
		T cur_top = array[1];    // the answer
		T tmp_down = array[size--];    // ignore the last element
		
		// flow down
		int father_ptr = 1;    // compare with the small child
		int child_ptr;
		while (father_ptr * 2 <= size){    // if father have left child, the position of left child is father_ptr * 2
											// if father have right child, it must have left child
											// if father_ptr * 2 > size, it have no children, because size is the position of the last element
			child_ptr = father_ptr * 2;
			if (child_ptr + 1 <= size && comp.compare(array[child_ptr + 1], array[child_ptr]) < 0)    // child_ptr point to min child
				child_ptr++;
			
			if (comp.compare(array[child_ptr], tmp_down) < 0 ){
				array[father_ptr] = array[child_ptr];
				father_ptr = child_ptr;
			}else{
				break;
			}
			
		}
		array[father_ptr] = tmp_down;
		
		return cur_top;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ans = "";
		if (!isEmpty()){
			for (int i = 1; i <= size; i++){
				ans += array[i];
				ans += " ";
			}
		}
		
		return ans;
	}
	
	
	
}
