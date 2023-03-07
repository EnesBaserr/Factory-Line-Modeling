import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.*;

import javax.xml.transform.Templates;

import org.w3c.dom.Node;

public class FactoryImpl implements Factory{
	private Holder first;
	private Holder last;
	private Integer size ;
	public Holder getFirst() {
		return first;
	}
	public FactoryImpl() {
		doClear();
	}
	public void clear() {
		doClear();
	}
	private void doClear() {
		first= null;
		last= null;
		
		size=0;
	}
	public int size() {
        return size;
    }
	public boolean isEmpty() {
		
        return size() == 0;
    }	
	@Override
	public void addFirst(Product product) {
		
		Holder newHolder= new Holder(null, product, null);
		if(size()==0) {
			first=last=newHolder;
			first.setPreviousHolder(null);
			last.setNextHolder(null);
		
			size++;
			
			
			
		}
		else {
			
			first.setPreviousHolder(newHolder);
			newHolder.setNextHolder(first);
			newHolder.setPreviousHolder(null);
			first=newHolder;
			
			
			size++;
			
			
		}	
		
	}
	@Override
	public void addLast(Product product) {
		Holder newHolder= new Holder(null, product, null);
		if(size==0) {
			first=last=newHolder;
			first.setPreviousHolder(null);
			last.setNextHolder(null);
			size++;
			
		}
		else if (size==1) {
			last.setNextHolder(newHolder);

			newHolder.setPreviousHolder(first);
			
			newHolder.setNextHolder(null);
			last=newHolder;
			
			size++;
			
			
		
			
		}
		else {
			
			last.setNextHolder(newHolder);
			newHolder.setPreviousHolder(last);
			last=newHolder;
			last.setNextHolder(null);
			
			
			size++;
		}	
		
	}
	@Override
	public Product removeFirst() throws NoSuchElementException {
		Holder tmpHolder = first;
		if(isEmpty()) {		
			throw new NoSuchElementException();
		}
		else {
			if(first.getNextHolder()==null) {
				first=null;
				last=null;
				size--;
			}
			else {
				first.getNextHolder().setPreviousHolder(null);
				first=first.getNextHolder();
				size--;
			}			
		}		
		return tmpHolder.getProduct();
	}
	@Override
	public Product removeLast() throws NoSuchElementException {
		Holder lasHolder;
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			
			Holder helpHolder=first;
			if(helpHolder.getNextHolder()==null) {
				
				first=null;
				last=null;
				size--;
				return helpHolder.getProduct();
				
			}
			else{
				while(helpHolder.getNextHolder().getNextHolder()!=null) {
				helpHolder=helpHolder.getNextHolder();			
			}
			lasHolder=helpHolder.getNextHolder();
			helpHolder.setNextHolder(null);
			lasHolder.setPreviousHolder(null);
			last=helpHolder;
			size--;
			
		}
		}
		return  lasHolder.getProduct();		
	}
	@Override
	public Product find(int id) throws NoSuchElementException {
		Holder temp = first;
		int counter=0;
		if(temp.getNextHolder()==null&&temp.getProduct().getId()==id) {
			return temp.getProduct();
			
		}
		
		while(temp.getNextHolder()!=null) {
		
		if(temp.getNextHolder()==last && temp.getNextHolder().getProduct().getId()==id) {
						
					return temp.getNextHolder().getProduct();	
		}
			
			
			if(temp.getProduct().getId()==id) {
				counter++;
				return temp.getProduct();
			}
			else {
				temp=temp.getNextHolder();
				if(temp.getNextHolder()==null&& temp.getProduct().getId()==id){
				return temp.getProduct();
				}
			}
			
		}
		
		
		
		throw new NoSuchElementException();		
	}
	
	@Override
	public Product update(int id, Integer value) throws NoSuchElementException {
		Product mainProduct=find(id);
		Product newProduct= new Product(find(id).getId(), find(id).getValue());
		Product finalProduct=mainProduct;
		Product xProduct=mainProduct;
		finalProduct.setValue(value);
		return newProduct;
		
	}
	@Override
	public Product get(int index) throws IndexOutOfBoundsException {
		Holder varHolder=first;
		
		if(size==1&& index==0) {
			
			return first.getProduct();
		}
		else {
		 if (index < 0 || index > size()-1) {
	            throw new IndexOutOfBoundsException();		
	}
		}
		 for(int i = 0;i<index;i++) {
			 varHolder=varHolder.getNextHolder();
		 }
		 return varHolder.getProduct();
		 
	}
	@Override
	public void add(int index, Product product) throws IndexOutOfBoundsException {
		Holder newHolder= new Holder(null,null,null);
		
		newHolder.setProduct(product);
		newHolder.setNextHolder(null);
		newHolder.setPreviousHolder(null);
		if(index<0 || index>size) {
			
			throw new IndexOutOfBoundsException();
			
		}
		else if(index==0) {
			
			addFirst(product);
			
		}
		else if(index==size()) {
			addLast(product);
		}
		else {
			Holder tmpHolder= new Holder(null, null, null);
			tmpHolder = first;
			for(int i = 1 ;i<index;i++) {
				if(tmpHolder!=null) {
					tmpHolder=tmpHolder.getNextHolder();
				}
			}
			if(tmpHolder!=null) {
				newHolder.setNextHolder(tmpHolder.getNextHolder());
				newHolder.setPreviousHolder(tmpHolder);
				tmpHolder.setNextHolder(newHolder);
				if(newHolder.getNextHolder()!=null)
					newHolder.getNextHolder().setPreviousHolder(newHolder);
				
				
			}
			size++;
		}
		
	}
	@Override
	public Product removeIndex(int index) throws IndexOutOfBoundsException {
		Holder pointerHolder1=first;
		Holder pointerHolder2=null;
		Product productReturned;
		if(index==0) {
			return removeFirst();
		}
		if(index==size()-1) {
			return removeLast();
		}
		if(index<0||index>size()-1) {
			throw new IndexOutOfBoundsException();
		}
		else {
			for(int i = 0 ;i<index;i++) {
				if(pointerHolder1.getNextHolder()==null) {
					return removeLast();
				}
				else {
					
				
				pointerHolder1 = pointerHolder1.getNextHolder();	
				
			}
			}
		
			
			
				pointerHolder2=pointerHolder1.getPreviousHolder();
				pointerHolder2.setNextHolder(pointerHolder1.getNextHolder());
				pointerHolder1.getNextHolder().setPreviousHolder(pointerHolder2);
				productReturned=pointerHolder1.getProduct();
				pointerHolder1=null;
				size--;
				return productReturned;	
				
							
		}		
	
		}
	@Override
	public Product removeProduct(int value) throws NoSuchElementException {
		Holder headHolder= first;
		Product aProduct=null ;
		int index=0;
		int counter = 0;
		for(int i = 0 ;i<size();i++) {
			if(headHolder.getProduct().getValue()==value) {
				counter++;
				if(counter==1) {
					
					
					aProduct=get(i);
					removeIndex(i);									
				}			
			}
			if(headHolder.getNextHolder()!=null) {
				headHolder=headHolder.getNextHolder();
			}			
		}
		if(counter==0) {
			throw new NoSuchElementException();			
		}
		else {
			return aProduct;
		}		
	}
	@Override
	public int filterDuplicates() {
		Holder headHolder= first;
		ArrayList<Integer> myStorageArrayList = new ArrayList<>();
		ArrayList<Integer> myStorageArrayList2 = new ArrayList<>();
		int index = 0;
		int counter=-1;
		int helper=0;
		int j =0;
		
		
		if(size==0 || size==1) {
			return helper;
		}
		else {
			
		
		for(int i = 0 ;i<(size());i++) {
		
			if(headHolder.getNextHolder()==null) {
				if(myStorageArrayList2.contains(headHolder.getProduct().getValue())) {

					j++;
					counter++;
					helper++;
					myStorageArrayList.add(i);
				}
				break;
			}
			if(myStorageArrayList2.contains(headHolder.getProduct().getValue())) {
				
				j++;
				counter++;
				helper++;
				myStorageArrayList.add(i);							
				if (headHolder.getNextHolder()!=null) {
					headHolder=headHolder.getNextHolder();	
				}								
			}
			else {
				myStorageArrayList2.add(headHolder.getProduct().getValue());
				
				if (headHolder.getNextHolder()!=null) {
					headHolder=headHolder.getNextHolder();	
				}	
				
				
			}
			
		}
		int counter2=0;
		for(int f = 0 ;f<myStorageArrayList.size();f++) {
			
			removeIndex(myStorageArrayList.get(f)-counter2);
			counter2++;			
		}	
		return counter+1;	
		}
	}
	@Override
	public void reverse() {
		Holder pointerHolder1=first;
		Holder pointerHolder2=null;
		last=pointerHolder1;
		
		while(pointerHolder1!=null) {
			pointerHolder2=pointerHolder1.getPreviousHolder();
			pointerHolder1.setPreviousHolder(pointerHolder1.getNextHolder());
			pointerHolder1.setNextHolder(pointerHolder2);
			pointerHolder1=pointerHolder1.getPreviousHolder();
			
			
		}
		
		if(pointerHolder2!=null) {
			first=pointerHolder2.getPreviousHolder();
		}
		
			
	
	}
	@Override
	public String toString() {
		Holder newHolder=new Holder(null,null,null);
		newHolder=first;
		int counter =0;
		StringBuilder astBuilder = new StringBuilder("");
		astBuilder.append("{");
		if(size==0) {
			astBuilder.append("}");
			
		}
		if(size==1) {
			astBuilder.append(newHolder.getProduct()+"}");
		}
		else {
			while(newHolder!=null) {
				if(newHolder.getNextHolder()!=null) {
					
					astBuilder.append(newHolder.getProduct()+",");
					newHolder=newHolder.getNextHolder();
				}
				else {
					astBuilder.append(newHolder.getProduct()+"}");
					newHolder=null;
				}
				
			}
		}
		
		
		
		
		String finalString = astBuilder.toString();
		
		return finalString;
		
	
	}
}
	

	
	

