package catching.pokemon;

/**
 * @author Michael Jaramillo
 * 
 * A timer class that runs in seconds
 */

public class Timer {
	double start, end, delay;
	
	public Timer()
	{
		start = 0;
		end = 0;
		delay = 0;
	}
	
	// 
	public void setDelay(double DELAY)
	{
		delay = DELAY;
	}
	
	public void start()
	{
		start = System.currentTimeMillis();
	}
	
	public void stop()
	{
		end = System.currentTimeMillis();
	}
	
	// returns true if the time passed is smaller than the delay
	public boolean hasTimePassed()
	{
		stop();
		return ((end - start) / 1000.0) < delay;
	}
	
	// method used to see how long something took to complete
	public double TimePassed()
	{
		return (end - start) / 1000.0;
	}
	
	// counts down every second from a given time 'n'
	public void CountDownTimer(int n)
	{
		int i = n;
		
		while(i >= 0)
		{
			setDelay(1.0);
			start();
			while(hasTimePassed()){}
			System.out.println(i);
			
			i--;
		}
	}
	
	//counts up every second to a given time 'n'
	public void CountUpTimer(int n)
	{
		int i = 0;
		
		while(i <= n)
		{
			setDelay(1.0);
			start();
			while(hasTimePassed()){}
			System.out.println(i);
			
			i++;
		}
	}
	
	
	public static void main(String args[])
	{
		Timer t = new Timer();
		t.setDelay(5);
		t.start();
		t.CountDownTimer(5);
	}
}
