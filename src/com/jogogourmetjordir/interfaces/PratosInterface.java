package com.jogogourmetjordir.interfaces;


public interface PratosInterface<T> {

	public void inserirPrato(T e) throws Exception;

	public void perguntar(T e);

	public T getArvore();

	public void setArvore(T e);
	
	

}
