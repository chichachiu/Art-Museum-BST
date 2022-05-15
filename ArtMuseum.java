import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 *
 */
public class ArtMuseum {
  private BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   *
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   *
   * @return the size of this ArtworkGallery
   */
  public int size() {
    return size;
  }

  /**
   * Checks whether this ArtworkGallery contains a Artwork given its name, year, and cost.
   *  The base case is the tree is empty; if the tree is not empty, we create a new artwork with
   *  the provided name and year and default cost and use it in the recursive helper method.
   *
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {
    //create a new artwork with the provided name and year and default cost and use it in the
    // search operation
    Artwork newArt = new Artwork(name, year, cost);
    return lookupHelper(newArt, root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current.
   *
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {
    if (current == null) {
      return false;
      //If they match, return true
    } else if (current.getData().compareTo(target)==0) {
      return true;
      //if the target is smaller, we recurse to the left
    } else if (current.getData().compareTo(target) > 0) {
      return lookupHelper(target, current.getLeft());
      //if the target is greater, we recurse to the right
    } else if (current.getData().compareTo(target) < 0) {
      return lookupHelper(target, current.getRight());
    }
    return false;
  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   *
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   *         there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) throws NullPointerException {
    if (newArtwork == null)
      throw new NullPointerException("newArtwork is null");
     else if (root == null) {
      root = new BSTNode<Artwork>(newArtwork);
      size++;
      return true;
    }
    if (addArtworkHelper(newArtwork, root)) {
      size++;
      return true;
    }
    return false;
  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   *  Based on compareTo() result, if the art work is same as current one, we should not add it;
   *  otherwise, if the target is greater than the current node, we recurse to right, if the target
   *  is smaller than the current node, we recurse to left. In each case we have to decide if the
   *  node is a leaf, which is the base case.
   *
   * @param current The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   *         with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {
    // if the art work is same as current one, we should not add it;
    if (current.getData().compareTo(newArtwork)==0) {
      return false;
    }
    BSTNode<Artwork> node = new BSTNode<>(newArtwork);
    //if the target is greater than the current node
    if (newArtwork.compareTo(current.getData()) > 0) {
      //if current node does not have right child, set this node as right child
      if (current.getRight() == null) {
        current.setRight(node);
        return true;
       //if current node has a right child, we recurse to right
      } else
        return addArtworkHelper(newArtwork, current.getRight());
    } else{
      //if the target is less than the current node
      if (current.getLeft() == null) {
        current.setLeft(node);
        return true;
      } else
        return addArtworkHelper(newArtwork, current.getLeft());
    }
  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery), the
   * largest artwork should be the rightest work. The largest node is the rightest node in BST.
   * We are going to consider three cases: tree is empty; only one node;more than one node
   *
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   *         ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {
    if (isEmpty())
      return null;
    else if (root.getLeft() == null && root.getRight() == null)
      return this.root.getData();
    else{
    BSTNode<Artwork> work = root;
    while (work.getRight()!=null) {
        work = work.getRight();
      }
    return work.getData();
    }
  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   *
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: $300.0)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: $550.0)]" + "\n"
   *
   * @return a String representation of all the artwork stored within this BST sorted in an
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method. We append the left node first, and then append the
   * current node, and then append the right node, so we append in increasing order.
   *
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {
    String output = "";
    if (current == null) {
      return output;
    }
    output += toStringHelper(current.getLeft());
    output += current.getData().toString() +"\n";
    output += toStringHelper(current.getRight());
    return output;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.If the tree is empty, node is 0, height is 0. If there is one node, height is 1.
   *
   * @return the height of this Binary Search Tree
   */
  public int height() {
      if (this.isEmpty())
        return 0;
      else if (root.getRight() == null && root.getLeft() == null)
        return 1;
      else
        return heightHelper(this.root);
    }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf. If there
   * are more than 1 node, we increment the height of left and right respectively, and choose the max
   * one.
   *
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {
    int leftHeight = 0;
    int rightHeight = 0;
    if (current.getLeft() != null)
      leftHeight = heightHelper(current.getLeft());
    if (current.getRight() != null)
      rightHeight = heightHelper(current.getRight());
    if (leftHeight > rightHeight)
      return leftHeight + 1;
    else
      return rightHeight + 1;
  }


  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.Base case
   * is the tree is empty, for recursive step, we use lookuphelper method to recurse.
   *
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {
    if (isEmpty())
      return new ArrayList<Artwork>();
     else
     return lookupAllHelper(year, cost, root);
  }

  /**
   * Recursive helper method to lookup the list of artworks given their year of creation and a
   * maximum value of cost. In this binary search, Year is the target key, we look for the node
   * which has the same key and the cost is not greater than cost, also the cost should greater than
   * 1000, if there is a match, we add it to the arrayList. We will consider 3 case, empty tree,
   * tree with one node, tree with more than one node
   *
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   *         this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
    BSTNode<Artwork> current) {
    ArrayList<Artwork> artWorkList = new ArrayList<Artwork>();
    if (current == null)
      return artWorkList;
    if (current.getData().getYear() == year && current.getData().getCost() <= cost) {
      artWorkList.add(current.getData());
    }
    artWorkList.addAll(lookupAllHelper(year, cost, current.getLeft()));
    artWorkList.addAll(lookupAllHelper(year, cost, current.getRight()));
    return artWorkList;
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   *
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria
   */

  public void buyArtwork(String name, int year, double cost) throws NoSuchElementException{
    Artwork artwork = new Artwork(name, year, cost);
    root = buyArtworkHelper(artwork, root);
    size--;
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree.
   *
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @param target  a reference to a Artwork we are searching to remove in the BST rooted at
   *                current.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current)
    throws NoSuchElementException {
    if (current == null) {
      throw new NoSuchElementException("No Artwork is found with the buying criteria in tree");
    }
    // Compare the target to the data at current and proceed accordingly
    // Recurse on the left or right subtree with respect to the comparison result
    if (target.compareTo(current.getData()) > 0) {
      current.setRight(buyArtworkHelper(target, current.getRight()));
    } else if (target.compareTo(current.getData()) < 0) {
      current.setLeft(buyArtworkHelper(target, current.getLeft()));
    } else {
      // current does not have children
      if (current.getLeft() == null && current.getRight() == null) {
        return null;
      }
      // current has two children
      else if (current.getLeft() != null && current.getRight() != null) {
        Artwork successor = getSuccessor(current);
        // Replace current with a new BSTNode whose data field value is the successor of target in
        // the
        // tree, and having the same left and right children as current.
        current = new BSTNode<Artwork>(successor, current.getLeft(), current.getRight());
        // Then, remove the successor from the right subtree. The successor must have up to one
        // child.
        current.setRight(buyArtworkHelper(successor, current.getRight()));
      } else if (current.getLeft() == null) {
        current = current.getRight();
      } else if (current.getRight() == null) {
        current = current.getLeft();
      }
    }
    // Make sure to return current at the end of the method.
    return current;
  }

  /**
   * Helper method to find the successor of a node in a BST (to be used by the delete operation).
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not null. If node does not have a right child, return node.getData().
   *
   * @param current current node
   *
   * @return returns without removing the key of the successor node
   */

  protected static Artwork getSuccessor(BSTNode<Artwork> current) {
    if (current == null)
      return null;

    BSTNode<Artwork> temp = current.getRight();
    while(temp.getLeft()!=null) {
      temp=temp.getLeft();
    }
    return temp.getData();
  }
}
