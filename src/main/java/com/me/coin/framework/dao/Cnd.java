package com.me.coin.framework.dao;

import java.util.ArrayList;
import java.util.List;



/**
 * 查询条件
 * @author dwl
 *
 */
public class Cnd {
	
	private List<Condition> cnds = new ArrayList<>();
	
	private List<String> orders = new ArrayList<>();
	
	private List<Object> params = new ArrayList<>();
	
	private Cnd (){}
	
	public static Cnd where(){
		return new Cnd();
	}
	
	
	public Cnd and(String column,String op,String value){
		this.cnds.add(new Condition(column, op, Op.And));
		this.params.add(value);
		return this;
	}
	
	
	public Cnd or(String column,String op,String value){
		this.cnds.add(new Condition(column, op, Op.Or));
		this.params.add(value);
		return this;
	}
	
	
	public Cnd orderBy(String order){
		this.orders.add(order);
		return this;
	}
	
	
	public Sql getSql(){
		StringBuilder where = new StringBuilder(" where ");
		for(int i=0;i<cnds.size();i++){
			if(i==0){
				if(cnds.get(i).getType().getType().equals("or"))
					throw new SqlAppendException("or不能作为第一个条件");
				where.append(cnds.get(i).getColumn()).append(cnds.get(i).getOp()).append(" ?");
			}else{
				String op = cnds.get(i).getType().getType();
				where.append(" ").append(op).append(" ").append(cnds.get(i).getColumn())
				     .append(cnds.get(i).getOp()).append(" ?");
			}
		}
		for(int i=0;i<orders.size();i++){
			if(i==0)
				where.append(" order by ").append(orders.get(i));
			else
				where.append(",").append(orders.get(i));
		}
		return new Sql(where.toString(),this.params);
	}
	
	
	

}
