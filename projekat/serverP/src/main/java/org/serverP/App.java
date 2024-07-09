package org.serverP;

import org.serverP.thread.ThreadServer;

/**
 * Hello world!
 *
 */
public class App 
{
	private static ThreadServer threadServer;
    public static void main( String[] args )
    {
    	if (threadServer == null || !threadServer.isAlive()) {
			System.out.println("zelimo da upalimo");
			threadServer = new ThreadServer();
			threadServer.start();
		}
    }
}
