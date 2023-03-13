package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {
	private static final int COUNT = 10;

	public static void main(String[] args) {
		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODO - start
		// create a client object and use it to
		Client c = new Client("temperatursensor", Common.BROKERHOST, Common.BROKERPORT);
		// - connect to the broker - user "sensor" as the user name
		c.connect();
		// - publish the temperature(s)
		for (int i = 0; i < COUNT; i++) {
			String s = "" + sn.read();
			c.publish(Common.TEMPTOPIC, s);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// - disconnect from the broker
		c.disconnect();

		System.out.println("Temperature device stopping ... ");
	}
}
