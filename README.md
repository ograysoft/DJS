# DJS
Distrubuted Java System

Cluster solution which allow to run java code on remote nodes.

System consists of two part:
- worker node
- client 

Worker node is REST micorservice which runs on all nodes in cluster it has endpoint /addclass which uploads .class and store in local pool
and /addtask which initiate one of preloaded with /addclass classes and start tasl

Client code is here:

  public static void main(String[] args) {
        Cluster cluster = new Cluster();
        cluster.addNode("http://localhost:8383");
        // add other nodes here
        // cluster.addNode("http://ip2:8383");

        try {
            // upload Task class, superclass and arguments classes to cluster
            addClassToCluster(cluster, DjsTask.class);
            addClassToCluster(cluster, Math2Task.class);
            addClassToCluster(cluster, MathTaskParam.class);
            addClassToCluster(cluster, MathTaskResponse.class);

            // executes remotely task
            HashMap<String,Integer> result = cluster.addTask(new Math2Task(1,2));
            for(String ip : result.keySet()) {
                System.out.println("Result from ["+ip+"] is "+result.get(ip));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
