package edu.umb.cs681.hw0015;

import java.time.LocalDateTime;

public class File extends FSElement {
	
	public File(Directory parent, String name, int size) {
		
		super(parent, name, size, LocalDateTime.now());
		isFile = true;
		isDir = false;
	}
	
	public boolean isDirectory() {
		return this.isDir;
	}
	
	public void setSize (int size) {
		this.size = size;
	}
	
	public int getSize () {
		return this.size;
	}
    
    
   

}
