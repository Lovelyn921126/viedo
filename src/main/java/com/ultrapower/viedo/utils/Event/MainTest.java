package com.ultrapower.viedo.utils.Event;

public class MainTest {
  public static void main(String[] args) {
	EventSourceObject object=new EventSourceObject();
	
	object.addCusListener(new CusEventListener() {
		@Override
		public void fireCusEvent(CusEvent e) {
			// TODO Auto-generated method stub
			super.fireCusEvent(e);
		}
	});
	 object.setName("eric");  
}
}
