import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
public class Project1{
	public static void main(String[] args) {	
		
	
	FactoryImpl factory = new FactoryImpl();
		try {
			FileWriter fileWriter=new FileWriter(args[1]);			
			File inputs = new File(args[0]);
			Scanner reader = new Scanner(inputs);
			
			while(reader.hasNextLine()) {			
				String inputInfo = reader.nextLine();
				String[] inputArray = inputInfo.split(" ");
				
				if(inputArray[0].equals("P")) {
					fileWriter.write(factory.toString()+"\n");
					
					
					
					
					
				}
				if(inputArray[0].equals("AF")) {
					
					Product xProduct=new Product(Integer.parseInt(inputArray[1]), Integer.parseInt(inputArray[2]));
					
					factory.addFirst(xProduct);
					
					
					
					
					
			            
					
								}
				if(inputArray[0].equals("AL")) {
					Product xProduct=new Product(Integer.parseInt(inputArray[1]), Integer.parseInt(inputArray[2]));
					factory.addLast(xProduct);
					
					
				}
				if(inputArray[0].equals("A")) {
					Product xProduct=new Product(Integer.parseInt(inputArray[2]), Integer.parseInt(inputArray[3]));
				
					try {
						factory.add(Integer.parseInt(inputArray[1]), xProduct);
					} catch (Exception e) {
						fileWriter.write("Index out of bounds."+"\n");
					}
					
					
					
				}
				if(inputArray[0].equals("RF")) {
					
					try {
						fileWriter.write(factory.removeFirst().toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Factory is empty."+"\n");
					}
					
				}
				if(inputArray[0].equals("RL")) {
					try {
						fileWriter.write(factory.removeLast().toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Factory is empty."+"\n");
					}
					
					
				}
				if(inputArray[0].equals("RI")) {
					int index = Integer.parseInt(inputArray[1]);
					try {
						fileWriter.write(factory.removeIndex(index).toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Index out of bounds."+"\n");
					}
					
				}
				if(inputArray[0].equals("RP")) {
					int value = Integer.parseInt(inputArray[1]);
					try {
						fileWriter.write(factory.removeProduct(value).toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Product not found."+"\n");
					}
					
				}
				if(inputArray[0].equals("F")) {
					int id = Integer.parseInt(inputArray[1]);
					try {
						fileWriter.write(factory.find(id).toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Product not found."+"\n");
					}
					
					
								}
				if(inputArray[0].equals("G")) {
					int index = Integer.parseInt(inputArray[1]);
					try {
						fileWriter.write(factory.get(index).toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Index out of bounds."+"\n");
					}
					
				}
				if(inputArray[0].equals("U")) {
					Product xProduct=new Product(Integer.parseInt(inputArray[1]), Integer.parseInt(inputArray[2]));
					int id = xProduct.getId();
					Integer value = xProduct.getValue();
					try {
						fileWriter.write(factory.update(id, value).toString()+"\n");
					} catch (Exception e) {
						fileWriter.write("Product not found."+"\n");
					}
					
				}
				if(inputArray[0].equals("FD")) {			
					fileWriter.write(factory.filterDuplicates()+"\n");;
				}
				if(inputArray[0].equals("R")) {
					factory.reverse();
					fileWriter.write(factory.toString()+"\n");
					
					
				}
				
				
				
			}
			
			reader.close();
			fileWriter.close();
			
			
		}
		
		
		catch (FileNotFoundException e) {
			
			
			// TODO: handle exception
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

		
	
