package collisionphysics;  

public class CollisionPhysics {  
	// Working copy for computing response in intersect(ContainerBox box),  
	// to avoid repeatedly allocating objects.  
	
	private static CollisionResponse tempResponse = new CollisionResponse();  
	/**  * Detect collision for a moving point bouncing inside a rectangular container,  
	 *  within the given timeLimit.  
	 *  If collision is detected within the timeLimit, compute collision time and  
	 *  response in the given CollisionResponse object. Otherwise, set collision time  
	 *   to infinity.  
	 *   The result is passed back in the given CollisionResponse object.  */  
	public static void pointIntersectsRectangleOuter(  
			float pointX, float pointY, float speedX, float speedY, float radius,  
			float rectX1, float rectY1, float rectX2, float rectY2,  float timeLimit, 
			CollisionResponse response) {  
		response.reset();  // Reset detected collision time to infinity  
		
		// A outer rectangular container box has 4 borders.  
		// Need to look for the earliest collision, if any.  
		
		// Right border  
		pointIntersectsLineVertical(pointX, pointY, speedX, speedY, radius, rectX2, timeLimit, tempResponse);  
		if (tempResponse.t < response.t) {  
			response.copy(tempResponse);  // Copy into resultant response  
		}  
		// Left border  
		pointIntersectsLineVertical(pointX, pointY, speedX, speedY, radius, rectX1, timeLimit, tempResponse); 
		if (tempResponse.t < response.t) {  
			response.copy(tempResponse);  
		}  
		// Top border  
		pointIntersectsLineHorizontal(pointX, pointY, speedX, speedY, radius, rectY1, timeLimit, tempResponse);  
		if (tempResponse.t < response.t) {  
			response.copy(tempResponse);  
		}  
		// Bottom border  
		pointIntersectsLineHorizontal(pointX, pointY, speedX, speedY, radius, rectY2, timeLimit, tempResponse);  
		if (tempResponse.t < response.t) {  
			response.copy(tempResponse);  
		}  
	} 
	
	/**  * Detect collision for a moving point hitting a horizontal line,  
	 *  within the given timeLimit.  */ 
	public static void pointIntersectsLineVertical(  
			float pointX, float pointY, float speedX, float speedY, float radius,  
			float lineX, float timeLimit, CollisionResponse response) {  
		response.reset();  // Reset detected collision time to infinity  
		
		// No collision possible if speedX is zero  
		if (speedX == 0) {  
			return;  
		}  
		
		// Compute the distance to the line, offset by radius.  
		float distance;  
		if (lineX > pointX) {  
			distance = lineX - pointX - radius;  
		} else {  
			distance = lineX - pointX + radius;  
		}  float t = distance / speedX;  // speedX != 0  
		// Accept 0 < t <= timeLimit  
		if (t > 0 && t <= timeLimit) {  
			response.t = t;  
			response.newSpeedX = -speedX;  // Reflect horizontally  
			response.newSpeedY = speedY;   // No change vertically  
		}  
	}  
	
	/**  * @see movingPointIntersectsLineVertical().  */  
	public static void pointIntersectsLineHorizontal(  
			float pointX, float pointY, float speedX, float speedY, float radius,  
			float lineY, float timeLimit, CollisionResponse response) {  
		response.reset();  // Reset detected collision time to infinity  
		
		// No collision possible if speedY is zero  
		if (speedY == 0) {  
			return;  
		}  
		// Compute the distance to the line, offset by radius.  
		float distance;  
		if (lineY > pointY) {  
			distance = lineY - pointY - radius;  
		} else {  
			distance = lineY - pointY + radius;  
		} 
		float t = distance / speedY;  // speedY != 0  
		// Accept 0 < t <= timeLimit  
		if (t > 0 && t <= timeLimit) { 
			response.t = t;  
			response.newSpeedY = -speedY;  // Reflect vertically 
			response.newSpeedX = speedX;   // No change horizontally  
		}  
	}  
}
		
		
		

		
		
		
		
	
		
		
		
		
	


