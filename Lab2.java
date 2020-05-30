/******************************************************************************

                            Online Java Debugger.
                Code, Run and Debug Java program online.
Write your code in this editor and press "Debug" button to debug program.

*******************************************************************************/
// From Faliq and Am SCSR Class
import java.util.*;

public class Lab2
{
	public static void main(String[] args) {
		System.out.println("Worstfit");
		
	//PARTITION BLOCK
	ArrayList<Integer> partition=new ArrayList<Integer>();
        ArrayList<String> status = new ArrayList<String>();
        
        String pass="Available";
        String stop="Unavailable";
        partition.add(0)   ;status.add("FOR OS PURPOSE ONLY");
        partition.add(9500);status.add(pass);
        partition.add(7000);status.add(pass);
        partition.add(4500);status.add(pass);
        partition.add(8500);status.add(pass);
        partition.add(3000);status.add(pass);
        partition.add(9000);status.add(pass);
        partition.add(1000);status.add(pass);
        partition.add(5500);status.add(pass);
        partition.add(1500);status.add(pass);
        partition.add(500);status.add(pass);
        
        //ArrayList<Integer> checkPart=(ArrayList<Integer>)partition.clone();
        //Collections.copy(checkPart,partition);
        
        int totalMemory = 0;
        for (int i: partition) {
            totalMemory += i;
        }
	
         // Here aList is an ArrayList of ArrayLists (in waiting job)
        ArrayList<ArrayList<Integer> > process = new ArrayList<ArrayList<Integer> >();

	process.add(new ArrayList<Integer>(Arrays.asList(0,2030,2,4)));

	process.add(new ArrayList<Integer>(Arrays.asList(0,5700,5,1)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,4190,4,2)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,3290,8,3))); 
        
        process.add(new ArrayList<Integer>(Arrays.asList(0,2550,2,5)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,6990,6,6)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,8940,8,7)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,740,10,8)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,3930,7,9)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,6890,6,10)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,6580,5,11)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,3820,8,12)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,9140,9,13)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,420,10,14)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,220,10,15)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,7540,7,16)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,3210,3,17)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,1380,1,18)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,9850,9,19)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,3610,3,20)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,7540,7,21)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,2710,2,22)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,8390,8,23)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,5950,5,24)));
        process.add(new ArrayList<Integer>(Arrays.asList(0,760,10,25)));


        //Queue (already in CPU)
        ArrayList<ArrayList<Integer> > queue = new ArrayList<ArrayList<Integer> >();
        //Usage of max Collection for Worst Fit purpose
        int max=Collections.max(partition);
        System.out.println("Max Partition :  "+max);
        
        //Process Part Assigned
        int block=0;
        int mem=1;
        int cpuBurst=2;
        int noJob=3;

	int t=1;
	int tPut=0;
        do{
		System.out.println("\n---------------------Time "+(t++)+"---------------------\n");
            //Reduce CPU cpuBurst
            for(int count=0;count<queue.size();){
                //reduce time by one second
                int redTime=queue.get(count).get(cpuBurst)-1;
                queue.get(count).set(cpuBurst,redTime);
                
                //if cpuBurst ==0 remove the process from queue
                if(redTime==0){
                    int recover=queue.get(count).get(block);
                    int plusMem=queue.get(count).get(mem);
                    queue.remove(count);
                    int newMem=partition.get(recover)*-1;
                    partition.set(recover,newMem);
                    //System.out.println("This means it will open another partition for available space\n");
                    //System.out.println("Partition  :  "+partition+" Status :  "+status+"\n");
                    status.set(recover,pass);
                    tPut++;
                }
                else{
                    //stay put the process in queue
                    count++;
                }   
        }
         
            //Check for maximum partition
            for(int i=0;i<process.size();){
                max=Collections.max(partition);
                int maxIndex=partition.indexOf(max);
                
                if(process.get(i).get(mem)<=partition.get(maxIndex)&&status.get(maxIndex)==pass){
                    process.get(i).set(block,maxIndex);
                    status.set(maxIndex,stop);
                    queue.add(process.get(i));
                    process.remove(i);
                    int newVal=partition.get(maxIndex)*-1;
                    partition.set(maxIndex,newVal);
                }
                else{
                    i++;
                }
            }
            
            int iFragment=0;
	    System.out.println(" ");
            System.out.println("Arranged According to Block");
            System.out.println("Job\tTime\tSize\t\n");
            for(int i=1;i<partition.size();i++){
                //System.out.print(partition.get(i)+"\t"+i+"\t");
                for(int j=0;j<queue.size();j++){
                    
                    if(queue.get(j).get(block)==i){
                        System.out.print(queue.get(j).get(noJob)+"\t"+
                                  queue.get(j).get(cpuBurst)+"\t"+
                                  queue.get(j).get(mem)+"\t"/*+
                                  queue.get(j).get(block)*/);
                        iFragment+=partition.get(i)+queue.get(j).get(mem);
                    
                        break;
                    }
                }
                System.out.println(" ");
            }
            iFragment=iFragment*-1;
            int hole=totalMemory-iFragment;
	/*
            System.out.println(" ");
	    System.out.println("Arranged According to Process(Already in Queue)");
            System.out.println("No. Job\tTime\tSize\tBlock Assigned\n");
            for(int i=0;i<queue.size();i++){
                System.out.println(queue.get(i).get(noJob)+"\t"+
                                  queue.get(i).get(cpuBurst)+"\t"+
                                  queue.get(i).get(mem)+"\t"+
                                  queue.get(i).get(block));
            }
          */  
            
            //Process (in waiting)== Not Allocated Yet
            int waitTime=0;
	    System.out.println(" ");
	    System.out.println("Arranged According to Process(Not Allocated Yet)");
            System.out.println("No. Job\tTime\tSize  --->  Not Allocated Yet");
            for(int i=0;i<process.size();i++){
               System.out.println(process.get(i).get(noJob)+"\t"+
               process.get(i).get(cpuBurst)+"\t"+
               process.get(i).get(mem));
               
               waitTime+=process.get(i).get(cpuBurst);
            }
            
     
            System.out.println("\n\n------------------------------------------------\n");
            //System Status Detail
            System.out.println("\n\nSystem Status\n");
            System.out.println("Throughput : "+tPut+"\n");
            System.out.println("Storage Utilization");
            int usedPercent=(queue.size()*100)/(partition.size()-1);
            System.out.println("\t*Used Partition   :  "+usedPercent+"%");
            int unusedPercent=100-usedPercent;
            System.out.println("\t*Unused Partition : "+unusedPercent+"%\n");
            System.out.println("Waiting Queue [JOBS] : "+process.size());
            System.out.println("Waiting Queue [TIME] : "+ waitTime+" \n");
            System.out.println("Internal Fragmentation(KB) : "+iFragment+"\n");
            System.out.println("Hole :"+hole+"\n");
            
    
        }while(queue.size()>0);
 
	}     
}

//Faliq and Am SCSR OS Class 19/20 --> The Great Lockdown

