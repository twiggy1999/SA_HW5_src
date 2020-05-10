package com.example.encoder;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.io.File;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class Controller implements CommandLineRunner{
    
    @Override
    public void run(String... strings){
        try{
		File thisfile = new File(System.getProperty("user.dir"));
		String parentPath = thisfile.getParent();
		String dataPath = parentPath;

		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
       
        Listener fileListener = new Listener(dataPath, queue);
        
		Thread fileListenerThread = new Thread(fileListener);
		fileListenerThread.start();

		Coder transcoder = new Coder(dataPath, queue);
		Thread processorThread = new Thread(transcoder);
        processorThread.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
		while (true) {
			try {
				Thread.sleep(9999999);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}