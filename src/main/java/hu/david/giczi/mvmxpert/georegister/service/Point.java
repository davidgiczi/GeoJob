package hu.david.giczi.mvmxpert.georegister.service;

import java.util.ArrayList;
import java.util.List;

public class Point {

	private final int id;
	private final double x;
	private final double y;
	private final double z;

	public Point(int id, double x, double y, double z) {

		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static List<Point> createMeasuredPointStore(List<Double> x_coords, List<Double> y_coords, List<Double> z_coords) {

		List<Point> store = new ArrayList<>();

		for (int i = 0; i < x_coords.size(); i++) {

			store.add(new Point(i + 1, x_coords.get(i), y_coords.get(i), z_coords.get(i)));

		}

		return store;

	}

	
	public static double calcDistanceAmongMeasuredPoints(List<Point> measuredPointStore) {
		
		double minDist = Double.MAX_VALUE;
		double sumDist = 0.0;
		Point standingPoint = measuredPointStore.get(0);
		List<Integer> standingPointIdStore = new ArrayList<>();
		int nextStandingPointIndex = 0;
		
		for (int i = 0; i < measuredPointStore.size(); i++) {
			
			minDist = Double.MAX_VALUE;
				
			for (int j = 0; j < measuredPointStore.size(); j++) {
				
				if(standingPoint.id != measuredPointStore.get(j).id &&
						minDist > getDistance(standingPoint, measuredPointStore.get(j)) &&
								!standingPointIdStore.contains(measuredPointStore.get(j).id) &&
								standingPoint.id + 1 == measuredPointStore.get(j).id) {
					
					minDist = getDistance(standingPoint, measuredPointStore.get(j));
					standingPointIdStore.add(standingPoint.id);
					nextStandingPointIndex = j;
				}
				
			}
			
			if(minDist != Double.MAX_VALUE) {
			sumDist += minDist;
			standingPoint = measuredPointStore.get(nextStandingPointIndex);
			}
		}
		
		
		return sumDist;
		
	}
	
	
	private static double getDistance(Point standingPoint, Point measuredPoint) {

		return Math.sqrt(Math.pow(standingPoint.x - measuredPoint.x, 2)
				+ Math.pow(standingPoint.y - measuredPoint.y, 2)
				+ Math.pow(standingPoint.z - measuredPoint.z, 2));
	}
	

	@Override
	public String toString() {
		return "Point [id=" + id + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
}
