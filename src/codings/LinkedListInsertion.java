package codings;

public class LinkedListInsertion {

	Node head;
	
	static class Node{
		int data;
		Node next;
		
		Node (int d){
			data=d;
			next=null;
		}
	}
	
	public void push(int new_data) {
	Node new_node=new Node(new_data);
		
	new_node.next=head;
	head=new_node;
		
	}
	public void insertAfterNode(Node prev_node, int new_data)
	{
		if(prev_node==null) {
			System.out.println("Can't add element");
			return;
		}
		Node new_node=new Node(new_data);
		new_node.next=prev_node.next;
		prev_node.next=new_node;
	}
	public void appeand(int new_data) {
		Node new_node=new Node(new_data);
		
		if(head==null) {
			head=new Node(new_data);
			return;
		}
		new_node.next=null;
		
		Node last=head;
		while(last.next!=null) {
			last = last.next;
		}
		last.next=new_node;
		return;
	}
	public void printll() {
		
		Node n=head;
		while(n!=null) {
			System.out.print(n.data+" ");
			
			n=n.next;
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		
		LinkedListInsertion ll=new LinkedListInsertion();
		//ll.appeand(27);
		//ll.push(79);
		
		ll.push(79);
		
		//ll.printll();
		//ll.appeand(23);
		ll.insertAfterNode(ll.head.next,8);
		ll.printll();

	}

}
