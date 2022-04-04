import java.util.*;
public class BinaryTreedemo {
    // declaration of node
    static class Node{ 
        int data;
        Node left;
        Node right;
        Node(int data){
             this.data=data;
             this.left=null;
             this.right=null;

        }
    }
        static class BinaryTree{
            // helps in traversing 
            static int idx= -1;
            // it will all the nodes info and return our root node
            public  Node BuildTree(int nodes[]){
               idx++;
               //if initially root is null value then their is no tree
               if(nodes[idx] == -1){
                   return null;
               }
               Node newnode = new Node(nodes[idx]);
               newnode.left = BuildTree(nodes);
               newnode.right = BuildTree(nodes);
               return newnode;

            }
        }
        // preorder, inorder and postorder are examples of DFS(Depth first search)
        // code for preorder
        // preorder first it will print root next left next right
        public static void preorder(Node root){
            if(root == null){
                return;
            }
               System.out.print(root.data +" ");
               preorder(root.left);
               preorder(root.right);
        }
         // code for inorder
        // inorder first it will print left next root next right
        public static void Inorder(Node root){
            if(root == null){
                return;
            }
            Inorder(root.left);
               System.out.print(root.data +" ");
               
               Inorder(root.right);
        }
         // code for postorder
        // postorder first it will print left  next right next root
        public static void postorder(Node root){
            if(root == null){
                return;
            }
            postorder(root.left);
               
               postorder(root.right);
               System.out.print(root.data +" ");
               
        }
        // level ordering is an example of BFS
        // we use queue to do this , we follow FIFO here
        public static void Levelorder(Node root){
            if(root == null){
                return;
            }
          Queue<Node> q= new LinkedList<>();
        //firstly we are adding root and null(used to know where we need to use next line i.e another level)
          q.add(root);
          q.add(null);

        //queue should not be empty
          while(!q.isEmpty()){
              Node currnode = q.remove(); // here we are removing the elements one by one according to the levels and store them in a list
              
              if(currnode == null){
                  //if currnode is null i.e it should be the leave then we need to go next level ,we need to print in another line
                  System.out.println();

                  if(q.isEmpty()){
                      break;//if this is the last leave then we should stop here
                  }else{
                      q.add(null);// if queue is empty then add null that means we have traversed all the elements by using FIFO and stored in list

                  }
                }
            
                  // this else will print the data
                  else{
                      System.out.print(currnode.data+" ");// it will print the data
                      if(currnode.left != null){
                         q.add(currnode.left);// if left node is not equals to null we need to print the data
                      }
                      if(currnode.right != null){
                        q.add(currnode.right);// if left node is not equals to null we need to print the data
                     }
                  }
              }
          
        }
        public static int count_of_nodes(Node root){
            // time complexity is O(n)
            if(root == null){
                return 0;
            }
            int leftNodes = count_of_nodes(root.left);
            int rightNodes = count_of_nodes(root.right);
            // by using recursion
            return leftNodes + rightNodes + 1;

        }
        public static int sum_of_nodes(Node root){
             // time complexity is O(n)
             if(root == null){
                return 0;
            }
            int leftsum = sum_of_nodes(root.left);
            int rightsum = sum_of_nodes(root.right);
            // by using recursion
            return leftsum + rightsum + root.data;
        }

        public static int height(Node root){
            if(root == null){
                return 0;
            }
            int leftheight = height(root.left);
            int rightheight = height(root.right);
            // by using recrsion
            int myheight = Math.max(leftheight,rightheight )+ 1; // 1 indicates the root
            return myheight;
        }
        public static int diameter(Node root){
            // timecomplexity is O(n^2) becoz we are using diff function i.e height
            if(root == null){
                return 0;
            }            
            int dia1 = diameter(root.left);
            int dia2 = diameter(root.right);
            int dia3 = height(root.left) + height(root.right) + 1;
             return Math.max(dia3, Math.max(dia1,dia2));

        }
        public static class Treeinfo{
            int ht;
            int dia;
            Treeinfo(int ht,int dia){
                this.ht = ht;
                this.dia = dia;
            }
        }
        public static Treeinfo diameter2(Node root){
            if(root == null){
               return  new Treeinfo(0,0);
            } 
            Treeinfo left = diameter2(root.left);
            Treeinfo right = diameter2(root.right);
            int myheight = Math.max(left.ht,right.ht )+ 1;
            int dia1 = left.dia;
            int dia2 = right.dia;
            int dia3 = left.ht + right.ht + 1;
             
            int mydia = Math.max(Math.max(dia1,dia2),dia3);
            Treeinfo myinfo = new Treeinfo(myheight, mydia);
            return myinfo;

        }
    public static void main(String args[]){
        // sequence
        // time complexity is O(n). because we need to traverse all he elements in the array
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree=new BinaryTree();
        Node root= tree.BuildTree(nodes);
        System.out.println("root of the tree is "+root.data);
        System.out.println("preorder is:");
        preorder(root);
        System.out.println("inorder is:");
        Inorder(root);
        System.out.println("postorder is:");
        postorder(root);
        System.out.println("levelorder is:");
        Levelorder(root);
        System.out.println(count_of_nodes(root));
        System.out.println(sum_of_nodes(root));
        System.out.println(height(root));
        System.out.println(diameter(root));
        System.out.println(diameter2(root).dia);
    }
}
