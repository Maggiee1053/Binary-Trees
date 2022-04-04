import java.util.*;
public class BinaryTreedemo {
    
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
            static int idx= -1;
            public  Node BuildTree(int nodes[]){
               idx++;
               if(nodes[idx] == -1){
                   return null;
               }
               Node newnode = new Node(nodes[idx]);
               newnode.left = BuildTree(nodes);
               newnode.right = BuildTree(nodes);
               return newnode;

            }
        }
 
        public static void preorder(Node root){
            if(root == null){
                return;
            }
               System.out.print(root.data +" ");
               preorder(root.left);
               preorder(root.right);
        }
   
        public static void Inorder(Node root){
            if(root == null){
                return;
            }
            Inorder(root.left);
               System.out.print(root.data +" ");
               
               Inorder(root.right);
        }
    
        public static void postorder(Node root){
            if(root == null){
                return;
            }
            postorder(root.left);
               
               postorder(root.right);
               System.out.print(root.data +" ");
               
        }
  
        public static void Levelorder(Node root){
            if(root == null){
                return;
            }
          Queue<Node> q= new LinkedList<>();
          q.add(root);
          q.add(null);
          while(!q.isEmpty()){
              Node currnode = q.remove();
              
              if(currnode == null){
                  System.out.println();
                  if(q.isEmpty()){
                      break;
                  }else{
                      q.add(null);

                  }
              }
                  else{
                      System.out.print(currnode.data+" ");
                      if(currnode.left != null){
                         q.add(currnode.left);
                      }
                      if(currnode.right != null){
                        q.add(currnode.right);
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
            // timecomplexity is O(n^2) 
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
