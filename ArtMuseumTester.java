import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 *
 */

public class ArtMuseumTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class. We test the case for empty
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    Artwork artwork1=new Artwork("Van Gogh",1890,100000000);
    Artwork artwork2=new Artwork("Van Gogh",1890,100000000);

    if(artwork1.compareTo(artwork2)!=0) {
      System.out.println("compareTo() fails when the artworks are same");
      return false;
    }

    Artwork artwork3=new Artwork("Picasso",1930,150000000);
    if(artwork1.compareTo(artwork3)==0) {
      System.out.println("compareTo() fails when the artworks are not same");
      return false;
    }

    //year is same?
    Artwork artwork4=new Artwork("Picasso",1920,150000000);
    if(artwork4.compareTo(artwork3)>=0) {
      System.out.println("compareTo() fails when this artwork is older than the otherArtwork");
      return false;
    }
    if(artwork3.compareTo(artwork4)<0) {
      System.out.println("compareTo() fails when this artwork is more new than the otherArtwork");
      return false;
    }

    //same year, cost is diff
    Artwork artwork5=new Artwork("Picasso",1940,155000000);
    Artwork artwork6=new Artwork("Picasso",1940,150000000);
    if(artwork6.compareTo(artwork5)>=0) {
      System.out.println("compareTo() fails when this artwork is less expensive than the otherArtwork");
      return false;
    }
    if(artwork5.compareTo(artwork6)<=0) {
      System.out.println("compareTo() fails when this artwork is more expensive than the otherArtwork");
      return false;
    }

    //same cost, same year different alphabetical order
    Artwork artwork7=new Artwork("Nobody",1940,155000000);
    if(artwork7.compareTo(artwork5)>=0) {
      System.out.println("compareTo() fails when this artwork is lower lexical than the otherArtwork");
      return false;
    }
    if(artwork5.compareTo(artwork7)<=0) {
      System.out.println("compareTo() fails when this artwork is higher lexical than the otherArtwork");
      return false;
    }

    if(!artwork1.equals(artwork2))
      return false;

    return true;
  }


  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    //1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty, and that its
    // string representation is an empty string
    ArtMuseum gallery = new ArtMuseum();
    boolean actual1 = gallery.isEmpty();
    if (!actual1) {
      System.out.println("isEmpty() fails for empty gallery");
      return false;
    }

    int actual2 = gallery.size();
    if (actual2 != 0) {
      System.out.println("size() fails for empty gallery");
      return false;
    }

    String actual3 = gallery.toString();
    if (!actual3.equals("")) {
      System.out.println("toString() fails for empty gallery");
      return false;
    }

    //2) try adding one artwork and then check that the addArtwork() method call returns true, the
    // tree is not empty, its size is 1, and toString() called on the tree returns the expected output.
    Artwork art1 = new Artwork("Gothic, Wood", 1932, 7000.0);
    boolean actual4 = gallery.addArtwork(art1);
    if (!actual4) {
      System.out.println( "failed to add first artwork");
      return false;
    }

    boolean actual5 = gallery.isEmpty();
    if (actual5) {
      System.out.println( "isEmpty() fails after adding first artwork");
      return false;
    }

    int actual6 = gallery.size();
    if (actual6 != 1) {
      System.out.println( "size() fails after adding the first artwork");
      return false;
    }

    String expect7 = "[(Name: Gothic, Wood) (Year: 1932) (Cost: $7000.0)]";
    String actual7 = gallery.toString().trim();
    if (!actual7.equals(expect7)) {
      System.out.println( "toString() fails after adding the first artwork");
      return false;
    }

    // 3) Try adding another artwork which is smaller that the artwork at the root,
    Artwork art2 = new Artwork("Last Dinner, DaVinci", 1503, 1000.0);
    boolean expect8 = true;
    boolean actual8 = gallery.addArtwork(art2);
    if (actual8 != expect8) {
      System.out.println( "failed to add the second artwork");
      return false;
    }

    if (gallery.size() != 2) {
      System.out.println( "size() fails after adding the second artwork");
      return false;
    }

    String expectString = "[(Name: Last Dinner, DaVinci) (Year: 1503) (Cost: $1000.0)]\n"
      + "[(Name: Gothic, Wood) (Year: 1932) (Cost: $7000.0)]";
    String actualString = gallery.toString().trim();
    if (!actualString.equals(expectString)) {
      System.out.println("toString() fails after adding the third artwork");
      return false;
    }

    // 4) Try adding a third artwork which is greater than the one at the root,
    Artwork art3 = new Artwork("Der Schrei, Silber", 2019, 12160.0);
    boolean actual9 = gallery.addArtwork(art3);
    if (!actual9) {
      System.out.println( "failed to add the third artwork");
      return false;
    }
    if (gallery.size() != 3) {
      System.out.println( "size() fails after adding the third artwork");
      return false;
    }
    String expectString1 = "[(Name: Last Dinner, DaVinci) (Year: 1503) (Cost: $1000.0)]\n"
      + "[(Name: Gothic, Wood) (Year: 1932) (Cost: $7000.0)]\n"
      + "[(Name: Der Schrei, Silber) (Year: 2019) (Cost: $12160.0)]";
    String actualString1 = gallery.toString().trim();
    if (!actualString1.equals(expectString1)) {
      System.out.println( "toString() fails after adding the third artwork");
      return false;
    }

    //5)Try adding at least two further artwork such that one must be added at the left subtree, and
    // the other at the right subtree.
    //this one should be added into left tree
    Artwork art4 = new Artwork("Whistler, Abbott", 1871, 5000.0);
    boolean actual10 = gallery.addArtwork(art4);
    if (!actual10) {
      System.out.println("failed to add the third artwork which should be added into left subtree");
      return false;
    }
    if (gallery.size() != 4) {
      System.out.println("size() fails after adding the third artwork which should be added into "
        + "left subtree");
      return false;
    }
    String expectString2 = "[(Name: Last Dinner, DaVinci) (Year: 1503) (Cost: $1000.0)]\n"
      + "[(Name: Whistler, Abbott) (Year: 1871) (Cost: $5000.0)]\n"
      + "[(Name: Gothic, Wood) (Year: 1932) (Cost: $7000.0)]\n"
      + "[(Name: Der Schrei, Silber) (Year: 2019) (Cost: $12160.0)]";
    String actualString2 = gallery.toString().trim();
    if (!actualString2.equals(expectString2)) {
      System.out.println(
        "toString() fails after adding the third artwork which should be added into left subtree");
      return false;
    }

    //this one should be added into right tree
    Artwork art5 = new Artwork("Guernica, Picasso", 1937, 3000.0);
    boolean actual11 = gallery.addArtwork(art5);
    if (!actual11) {
      System.out.println( "failed to add the fourth artwork to the right tree");
      return false;
    }
    if (gallery.size() != 5) {
      System.out.println("size() failed after adding the fourth artwork to the right tree");
      return false;
    }
    String expectString3 = "[(Name: Last Dinner, DaVinci) (Year: 1503) (Cost: $1000.0)]\n"
      + "[(Name: Whistler, Abbott) (Year: 1871) (Cost: $5000.0)]\n"
      + "[(Name: Gothic, Wood) (Year: 1932) (Cost: $7000.0)]\n"
      + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]\n"
      + "[(Name: Der Schrei, Silber) (Year: 2019) (Cost: $12160.0)]";
    String actualString3 = gallery.toString().trim();
    if (!actualString3.equals(expectString3)) {
      System.out.println("toString() failed after adding the fifth artwork to the right tree");
      return false;
    }

    //(6) Try adding a artwork already stored in the tree.
    boolean expect12 = false;
    boolean actual12 = gallery.addArtwork(art5);
    if (actual12 != expect12) {
      System.out.println( "failed to add");
      return false;
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */

  public static boolean testLookup() {
    //(1) Create a new ArtworkGallery. Then, check that calling the lookup() method on an empty
    // ArtworkGallery returns false.
    ArtMuseum gallery = new ArtMuseum();
    if(gallery.lookup("Van Gogh", 1830, 3000)) {
      System.out.println("lookup() fails for empty gallery");
      return false;
    }
    //(2) Consider a ArtworkGallery of height 3 which lookup at least 5 artwork.
    gallery.addArtwork(new Artwork("Starry Night, Van Gogh",1889,2000));
    gallery.addArtwork(new Artwork("Mona Lisa, DaVinci",1503,3000));
    gallery.addArtwork(new Artwork("The Cafe Terrace at Night, Van Gogh",1888,3000));
    gallery.addArtwork(new Artwork("Der Schrei, Edvard Munch",1893,4000));
    gallery.addArtwork(new Artwork("The Persistence of Memory, Dali",1931,3000));

    //Then, try to call lookup() method to search for the artwork having a match at the root of the
    // tree.
    if(!gallery.lookup("Starry Night, Van Gogh", 1889, 2000)) {
      System.out.println("lookup() fails when searching for the root");
      return false;
    }

    //(3) Then, search for an artwork at the right and left subtrees at different levels considering
    // successful and unsuccessful search operations. Make sure that the lookup() method returns the
    // expected output for every method call.

    //search for an artwork at the left subtrees at the third level, should be successful
    if(!gallery.lookup("Mona Lisa, DaVinci", 1503, 3000)) {
      System.out.println("lookup() fails when search for the artwork at the left tree, at second"
        + "level");
      return false;
    }

    //search for an artwork at the left subtrees at the third level, should be successful
    if(!gallery.lookup("The Cafe Terrace at Night, Van Gogh", 1888, 3000)){
      System.out.println("lookup() fails when search for the artwork at the left tree, at third "
        + "level");
      return false;
    }

    //search for an artwork at the right subtrees at the second level, should be successful
    if(!gallery.lookup("Der Schrei, Edvard Munch", 1893, 4000)) {
      System.out.println("lookup() fails");
      return false;
    }

    //search for an artwork at the right subtrees at the third level, should be successful
    if(!gallery.lookup("The Persistence of Memory, Dali", 1931, 3000)) {
      System.out.println("lookup() fails on smallest node");
      return false;
    }

    //search for an artwork does not exist, should be unsuccessful
    if(gallery.lookup("NightHawks, Hopper",1942,7000)) {
      System.out.println("should not return true at wrong target");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4.
   *               (*)
   *              /  \
   *            (*)  (*)
   *             \   / \
   *            (*) (*) (*)
   *                    /
   *                   (*)

   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
      //(1) ensures that the height of an empty artwork tree is zero.
      ArtMuseum gallery = new ArtMuseum();
      int heightExpected = 0;
      if (gallery.height() != heightExpected) {
        System.out.println("height() fails on empty tree");
        return false;
      }

      // (2) ensures that the height of a tree which consists of only one node is 1.
      gallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1530, 3000));
      if (gallery.height() != 1) {
        System.out.println("height() fails on three nodes");
        return false;
      }

      //(3) ensures that the height of a ArtworkGallery with the following structure for instance is 4.
      gallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1830, 2000));
      gallery.addArtwork(new Artwork("The Pub, Van Gogh", 1860, 5000));
      gallery.addArtwork(new Artwork("Sunflower, Van Gogh", 1820, 5000));
      gallery.addArtwork(new Artwork("Dali", 1870, 7000));
      if (gallery.height() != 4) {
        System.out.println("height() fails on heigh of 4");
        return false;
      }

      return true;
    }


  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method. We check the cases for
   * empty tree, tree without child, tree with more children
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    ArtMuseum gallery = new ArtMuseum();
    if(gallery.getBestArtwork()!=null)
      return false;

    ArtMuseum gallery1 = new ArtMuseum();
    gallery1.addArtwork(new Artwork("Mona Lisa, DaVinci",1530,3000));

    if(gallery1.getBestArtwork().getYear()!=1530 ||gallery1.getBestArtwork().getCost()!=3000||
      !gallery1.getBestArtwork().getName().equals("Mona Lisa, DaVinci")){
      System.out.println("1");
      return false;

    }
    ArtMuseum gallery2 = new ArtMuseum();
    gallery2.addArtwork(new Artwork("Starry Night, Van Gogh",1830,2000));
    gallery2.addArtwork(new Artwork("The Pub, Van Gogh",1860,5000));
    gallery2.addArtwork(new Artwork("Sunflower, Van Gogh",1820,5000));
    gallery2.addArtwork(new Artwork("Dali",1870,7000));

    if(gallery1.getBestArtwork().getYear()!=1530 ||gallery1.getBestArtwork().getCost()!=3000||
      !gallery1.getBestArtwork().getName().equals("Mona Lisa, DaVinci")) {
      return false;
    }
      return true;
  }


  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty
   * arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method
   * returns an array list which contains all the artwork satisfying the search criteria of year and
   * cost, when called on a non empty artwork tree with one match, and two matches and more. Vary
   * your search criteria such that the lookupAll() method must check in left and right subtrees.
   * (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    //(1) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
    // on an empty tree.
    ArtMuseum gallery1 = new ArtMuseum();
    ArrayList<Artwork> actual1 = gallery1.lookupAll(2022, 2000.0);
    if (!actual1.isEmpty()) {
      System.out.println(
        "lookupAll() fails for empty gallery");
      return false;
    }

    //(2) Ensures that the ArtworkGallery.lookupAll() method returns an array list which contains
    // all the artwork satisfying the search criteria of year and cost, when called on a non-empty
    // artwork tree with one match, and two matches and more. Vary your search criteria such that
    // the lookupAll() method must check in left and right subtrees.
    ArtMuseum gallery2 = new ArtMuseum();
    Artwork art1 = new Artwork("Sunflower, VanGogh", 1930, 6000.0);
    Artwork art2 = new Artwork("Egg, DaVinci", 1930, 1000.0);
    Artwork art3 = new Artwork("Whistler, Abbott", 1871, 5000.0);
    Artwork art4 = new Artwork("Guernica, Picasso", 1930, 7000.0);
    Artwork art5 = new Artwork("Der Schrei, Silber", 2019, 12160.0);
    gallery2.addArtwork(art1);
    gallery2.addArtwork(art2);
    gallery2.addArtwork(art3);
    gallery2.addArtwork(art4);
    gallery2.addArtwork(art5);
    //should be successful
    ArrayList<Artwork> actualArrayList = gallery2.lookupAll(1930, 7000.0);
    String expect2 = "[(Name: Sunflower, VanGogh) (Year: 1930) (Cost: $6000.0)]\n"
      + "[(Name: Egg, DaVinci) (Year: 1930) (Cost: $1000.0)]\n"
      + "[(Name: Guernica, Picasso) (Year: 1930) (Cost: $7000.0)]";
    String actual2 = "";
    for (int i = 0; i < actualArrayList.size(); i++) {
      actual2 += actualArrayList.get(i).toString() + "\n";
    }
    if (!(actual2.trim()).equals(expect2)) {
      System.out.println(
        "lookupAll() fails for a non-empty gallery with three artwork match");
      return false;
    }

    //change criteria, should not be successful
    ArrayList<Artwork> actualArrayList3 = gallery2.lookupAll(1930, 500.0);
    if (actualArrayList3.size() != 0) {
      System.out.println("lookupAll() fails for a non-empty gallery with no artwork match");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    ArtMuseum gallery1 = new ArtMuseum();
    gallery1.addArtwork(new Artwork("Gothicc, Wood", 1930, 6000.0));
    gallery1.addArtwork(new Artwork("Mona Lisa, Davinci", 1503, 1000.0));
    gallery1.addArtwork(new Artwork("Whistler, Abbott", 1871, 5000.0));
    gallery1.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000.0));
    gallery1.addArtwork(new Artwork("Der Schrei, Silber", 2019, 12160.0));
    String expect1 = "[(Name: Mona Lisa, Davinci) (Year: 1503) (Cost: $1000.0)]\n"
      + "[(Name: Whistler, Abbott) (Year: 1871) (Cost: $5000.0)]\n"
      + "[(Name: Gothicc, Wood) (Year: 1930) (Cost: $6000.0)]\n"
      + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]";
    //(1) Buying artwork that is at leaf node at the rigt subtree
    gallery1.buyArtwork("Der Schrei, Silber", 2019, 12160.0);
    String actual1 = gallery1.toString().trim();
    if (!actual1.equals(expect1)) {
      System.out.println("BuyArtwork() fails when buying artwork at leaf node");
      return false;
    }

    String expect2 = "[(Name: Whistler, Abbott) (Year: 1871) (Cost: $5000.0)]\n"
      + "[(Name: Gothicc, Wood) (Year: 1930) (Cost: $6000.0)]\n"
      + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]";
    //(2) Buying artwork at non-leaf node
    gallery1.buyArtwork("Mona Lisa, Davinci", 1503, 1000.0);
    String actual2 = gallery1.toString().trim();
    if (!actual2.equals(expect2)) {
      System.out.println("BuyArtwork() fails when buying artwork at non-leaf node");
      return false;
    }

    ArtMuseum gallery2 = new ArtMuseum();
    gallery2.addArtwork(new Artwork("Sunflower, VanGoghh", 1930, 6000.0));
    gallery2.addArtwork(new Artwork("Mona Lisa, DaVinci", 1500, 1000.0));
    gallery2.addArtwork(new Artwork("Whistler, Abbott", 1871, 5000.0));
    gallery2.addArtwork(new Artwork("Guernica, Picasso", 1870, 3000.0));
    gallery2.addArtwork( new Artwork("Der Schrei, Silber", 1944, 12000.0));
    String expect3 = "[(Name: Guernica, Picasso) (Year: 1870) (Cost: $3000.0)]\n"
      + "[(Name: Whistler, Abbott) (Year: 1871) (Cost: $5000.0)]\n"
      + "[(Name: Sunflower, VanGoghh) (Year: 1930) (Cost: $6000.0)]\n"
      + "[(Name: Der Schrei, Silber) (Year: 1944) (Cost: $12000.0)]";
    //buying artwork which is leaf node at the left subtree
    gallery2.buyArtwork("Mona Lisa, DaVinci", 1500, 1000.0);
    String actual3 = gallery2.toString().trim();
    if (!actual3.equals(expect3)) {
      System.out.println("BuyArtwork() fails when buying artwork the the left subtree which is lead"+
        "node");
      return false;
    }

    // (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException when
    // called on an artwork that is not present in the BST
    try{
    try {
      gallery2.buyArtwork("Name: Nobody", 2022, 2000.0);
    }catch (NoSuchElementException e1) {
      System.out.println(e1.getMessage());
    }
    }catch (Exception e) {
      System.out.println("Catch wrong exception");
      return false;
    }

    return true;

  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   *
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {
    return testAddArtworkToStringSize() && testBuyArtwork() && testHeight() && testGetBestArtwork()
      && testArtworkCompareToEquals() && testLookupAll() & testLookup()
      && testAddArtworkToStringSize();
  }

  /**
   * Calls the test methods
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArworkCompareToEquals(): " + testArtworkCompareToEquals());
    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll());
    System.out.println("testBuyArtwork(): " + testBuyArtwork());
    System.out.println("runAllTests(): " + runAllTests());
  }

}
