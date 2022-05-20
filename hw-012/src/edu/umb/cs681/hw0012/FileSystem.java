package edu.umb.cs681.hw0012;


public class FileSystem implements Runnable{
	
	private static FileSystem instance;
	public Directory root = null;
	private int tab;

	private FileSystem() {}
	
	public static FileSystem getFileSystem(){
		
		clearSystem();
		
		if (instance == null){
			instance = new FileSystem();
			
		}
		return instance;
	}
	
	private static void clearSystem(){
		instance = null;
	}
	
	public Directory getRootDirectory(){
		if (this.root == null){
				this.root = new Directory(null, "root");
			}
		
		return this.root;
	}

	
	public String getTab(){
		
		String tabs = "";
		String tab = "\t";
		
		for(int i=0; i < this.tab; i++){
			
			tabs = tabs + tab;
		}
		
		return tabs;
		
	}

	
	@Override
	public void run() {
		//System.out.println("File System: ");
		
		FileSystem fileSystem = FileSystem.getFileSystem();
		
		File rand = new File(fileSystem.getRootDirectory(), "random file", 5);
		
		Directory system = new Directory(fileSystem.root, "system");
		File a = new File(system, "a.jpg", 10);
		File b = new File(system, "b.jpg", 45);
		File c = new File(system, "c.jpg", 6);
		
		
		Directory root = new Directory(fileSystem.root, "home");
		File f1 = new File(root, "f1.txt", 17);
		
		Directory docs = new Directory(root, "pictures");
		@SuppressWarnings("unused")
		File f2 = new File(docs, "f2.txt", 5);
		File f3 = new File(docs, "f3.txt", 78);
		
		fileSystem.root.appendChild(system);
		fileSystem.root.appendChild(root);
		fileSystem.root.appendChild(rand);
		
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		
		docs.appendChild(root);
		root.appendChild(docs);
		
		
		System.out.print(" " + root.getChildren() + "\n");
		System.out.println("home is a directory ? : " + root.isDirectory());
		System.out.println("\"f3.txt\" is a directory ? : " + f3.isDirectory());
		System.out.println("Total size : " + system.getTotalSize());
		System.out.println("Size of \"f1.txt\" : " + f1.getSize());
	}
	
	public static void main (String args[]) throws InterruptedException{

		for(int i=0;i<2;i++){
			Thread thread1 = new Thread(new FileSystem());
			System.out.print("Children of thread" +  (i+1) +" : ");
			
		    thread1.start();
		    Thread.sleep(1000);
		}
		
		
	}

	

}
