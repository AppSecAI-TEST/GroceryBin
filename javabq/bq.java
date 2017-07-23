package yiji.msgsev;
import java.lang.*;
import java.util.concurrent.*;
import java.util.*;
interface FailCallback{
	//will be called when the receiver message queue cannot add.
	//The instance must be added to the MsgSev instance.
	public void queueFull(MsgClient client,Message msg);
}
class Message{
	private MsgClient sender,receiver;
	private Object msg;
	public Message(MsgClient sender,MsgClient receiver,Object msg){
		//the null receiver means boardcast.
		//So there isn't check the receiver.
		if(sender==null||msg==null)
			throw new RuntimeException("Sender or Message is null!");
		this.sender=sender;
		this.receiver=receiver;
		this.msg=msg;
	}
	public MsgClient getSender(){return sender;}
	public MsgClient getReceiver(){return receiver;}
	public Object getMsg(){return msg;}
}
class MsgClient{
	public MsgClient(String name){
		this.name=name;
		this.bq=new LinkedBlockingQueue<Message>();
	}
	private String name;
	public BlockingQueue<Message> bq;
	public String getName(){return name;}
	@Override
	public boolean equals(Object o){
		//Only compare the name string.
		MsgClient m=(MsgClient)o;
		if(m.getName().equals(name)){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode(){
		//return the name string's hashcode.
		//In a MsgSev, the name string shouldn't be repeat.
		return name.hashCode();
	}
}
class MsgSev{
	FailCallback fcb=null;
	private Map<String,MsgClient> clientset=null;
	//Should only be called by "signUp" method.
	//Shouldn't be called out of the class.
	public void addClient(MsgClient msgcli){
		if(clientset.containsKey(msgcli.getName())){
			throw new RuntimeException("MsgCliemt is existed.");
		}
		clientset.put(msgcli.getName(),msgcli);
	}
	public void removeClient(String clientname){
		if(clientset.remove(clientname)==null){
			throw new RuntimeException("MsgClient not exist!");
		}
	}
	public void signUp(String name){
		MsgClient m=new MsgClient(name);
		addClient(m);
	}
	public void sendMsg(Message msg){
		if(!clientset.containsKey(msg.getSender().getName())){
			throw new RuntimeException("Sender not exist!");
		}
		if(msg.getReceiver()==null){
			//Boardcast message.
			Iterator<String> it=clientset.keySet().iterator();
			while(it.hasNext()){
				MsgClient mc=clientset.get(it.next());
				if(!mc.bq.offer(msg)){
					//If client message queue is full.
					fcb.queueFull(mc,msg);
				}
			}
		}else{
			//Unicast message.
			if(!clientset.containsKey(msg.getReceiver().getName())){
				throw new RuntimeException("Receiver not exist!");
			}
			//TODO
		}

	}
}
