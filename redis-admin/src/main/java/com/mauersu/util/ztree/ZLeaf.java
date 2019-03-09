package com.mauersu.util.ztree;

public class ZLeaf implements Comparable<ZLeaf> {
	
	protected String name;
	
	protected Object attach;
	
	protected ZLeaf() {
		
	}
	public Object getAttach() {
		return attach;
	}

	public void setAttach(Object attach) {
		this.attach = attach;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @description 
	 * @param o
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public int compareTo(ZLeaf o) {
		if(o == null) return 1;
		if(o instanceof ZLeaf) {
			ZLeaf zlo = (ZLeaf) o;
			return this.getName().compareTo(zlo.getName());
		}
		ZLeaf zlo = (ZLeaf) o;
		return this.getName().compareTo(zlo.getName());
	}
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o instanceof ZLeaf) {
			ZLeaf zlo = (ZLeaf) o;
			return this.getName().equals(zlo.getName());
		}
		if(o instanceof String) {
			return this.getName().equals(o);
		}
		return false;
	}
	
}
