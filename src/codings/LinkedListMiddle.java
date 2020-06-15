package codings;

public class LinkedListMiddle {
	
	Node head;
	
	 class Node{
		int data;
		Node next;
		
		Node(int d){
			data=d;
			next=null;
		}
	}
	
	public void push(int new_data){
		
		Node new_node=new Node(new_data);
		
		new_node.next=head;
		head=new_node;
		
	}
	
	public void prinll() {
		Node n= head;
		
		while(n!=null) {
			System.out.print(n.data+" --> ");
			n=n.next;
		}
	}
	
	public void MiddleOfll() {
		Node fast_ptr=head;
		Node slow_ptr=head;
		while((fast_ptr!=null) && (fast_ptr.next!=null)) {
			
			fast_ptr=fast_ptr.next.next;
			slow_ptr=slow_ptr.next;
		}
		
		System.out.println("Middle Element is : "+slow_ptr.data);
		
	}

	public static void main(String[] args) {

		LinkedListMiddle ll=new LinkedListMiddle();
		ll.push(1);
		ll.push(2);
		ll.push(3);
		ll.push(4);
		//ll.push(5);
		
		ll.prinll();
		ll.MiddleOfll();
	}

}
