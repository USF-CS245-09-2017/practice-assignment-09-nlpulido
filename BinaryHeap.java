import java.util.Arrays;

public class BinaryHeap {
	int[] data;
	int size;

	/* constructor */
	public BinaryHeap(){
		data = new int[10];
		size = 0;
	}

	/*
	* This add method will insert a value at the latest position 
	* in order to create a complete tree.
	*/ 
	public void add(int value){
		if (data.length == size + 1){
			growArray();
		}
		data[size++] = value;
		int current = size - 1;
		int parent = (current - 1) / 2;
		while ((parent >= 0) && (data[current] < data[parent])){
			swap(data, current, parent);
			current = parent;
			parent = (parent - 1) / 2;
		}
	}

	/*
	* This remove method will remove the root (as it contains highest priority)
	* then shift down all values.
	*/
	public int remove(){
		swap(data, 0, size - 1);
		--size;
		if (size != 0){
			shiftDown(0);
		}
		return (data[size]);
	}

	/*
	* This shift down method will compare the children along with their parent and
	* make the lowest value of the 3 to be the new parent.
	*/
	public void shiftDown(int position){
		int lowestChild;

		while (!isLeaf(position)){
			lowestChild = ((position * 2) + 1);

			if ((lowestChild < size - 1) && (data[lowestChild] > data[lowestChild + 1])){
				lowestChild = lowestChild + 1;
			}

			if (data[position] <= data[lowestChild]){
				return;
			}

			swap(data, position, lowestChild);
			position = lowestChild;
		}
	}

	private boolean isLeaf(int pos){
		return ((pos >= (size) / 2) && (pos < size));
	}

	private void growArray(){
		data = Arrays.copyOf(data, data.length * 2);
	}

	private void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}