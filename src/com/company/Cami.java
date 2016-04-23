package com.company;

/**
 *
 * @author adri
 */
public class Cami {
    private String cami;
    private String descripcio;
    
    public Cami(){
        this.cami=null;
        this.descripcio =null;
    }

    public Cami(String cami, String descripcio) {
        this.cami = cami;
        this.descripcio = descripcio;
    }

    public void set_cami(String cami) {
        this.cami = cami;
    }

    public void set_descripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String get_cami() {
        return this.cami;
    }

    public String get_descripcio() {
        return this.descripcio;
    }
    
    private String cami_definitiu(String cami){
    	if(cami.length()%2==0){
    		char[] aux=cami.toCharArray();
    		char[] ret=new char[cami.length()+1];
    		int i;
    		for(i=0; i<cami.length()/2;i++){
    			ret[i]=aux[i];
    		}
    		ret[i]='E';
    		for(i=i;i<cami.length();i++){
    			ret[i+1]=aux[i];
    		}
    		return new String(ret);
    		
    	}
    	else return cami;
    }
    
    public boolean es_concatenable(String cami){
        char aux1[]=cami.toCharArray();
        char aux2[]=this.cami.toCharArray();        
        return aux2[aux2.length-1]==aux1[0];
    }
    
    public void concatenar(String cami){
        char aux[]=this.cami.toCharArray();
        if(aux[this.cami.length()/2+1]=='E'){
        	char aux1[]=new char[this.cami.length()];
        	int j=0;
        	for(int i=0; i<this.cami.length(); i++){
        		if(i==this.cami.length()/2+1) i++;
        		aux1[j]=aux[i];
        		j++;
        	}
        	aux=aux1;
        }
        
        String s=new String(aux);
        char aux1[]=new char[s.length()+cami.length()-1];
        for(int i=0; i<s.length();i++){
        	aux1[i]=aux[i];
        }
        
        char aux2[]=cami.toCharArray();
        for(int i=1;i<cami.length();i++){
        	aux1[i+s.length()-1]=aux2[i];
        }
        String ret=new String(aux1);
        this.cami=cami_definitiu(ret);
    }
    
    public void invertir(){
        char[] aux=cami.toCharArray();
        int end=cami.length()-1;
        int begin=0;
        char temp;
        while(end>begin){
            temp=aux[begin];
            aux[begin]=aux[end];
            aux[end]=temp;
            end--;
            begin++;
        }
        cami=new String(aux);   
        
    }
    
    public int get_longitud(){
        return cami.length();
    }
    
    public boolean cami_valid(String cami){
    	char[] aux = cami.toCharArray();
    	for(int i=0; i < cami.length()-1; i++){
    		if(aux[i]!='P' && aux[i]!='C' && aux[i]!='A' &&  aux[i]!='T') return false;
    		else if(aux[i]=='P' && aux[i+1]=='P') return false;
    		else if((aux[i]=='C' || aux[i]=='A' || aux[i]=='T') && (aux[i+1]!='P')) return false;
    		
    	}
    	return true;
    }
    
    
}
