package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order
{
	public Long orderId;
	public List<OrderLine> orderLines = new ArrayList<OrderLine>();
	public Payment payment;
	public Employee employee;
}
