package Control;

import Model.BinaryTree;
import View.DrawingPanel;
import View.TreeView.TreeNode;
import View.TreeView.TreePath;

/**
 * Created by Jean-Pierre on 12.01.2017.
 */
public class MainController {

    private final BinaryTree<String> binaryTree;

    public MainController(){
        binaryTree = new BinaryTree<>(""); // Ein Baum ohne Wurzel-Inhalt ist auf dauer ein leerer Baum - es lassen sich laut Dokumentation nichtmal Bäume anhängen. Also wird die Wurzel gefüllt.
        createMorseTree();
    }

    /**
     * Zur Präsentation des Programms wird der Morsecode im Baum dargestellt.
     */
    private void createMorseTree(){
        //TODO 02: Vervollständige den Morsebaum. Such bei google nach "morsecode as tree" als Vorlage. Das hilft, die Übersicht zu wahren.
        BinaryTree<String> left = new BinaryTree<>("E");
        BinaryTree<String> right = new BinaryTree<>("T");

        binaryTree.setLeftTree(left);
        binaryTree.setRightTree(right);

        left = new BinaryTree<>("I");
        right = new BinaryTree<>("A");
        binaryTree.getLeftTree().setLeftTree(left);
        binaryTree.getLeftTree().setRightTree(right);

        left = new BinaryTree<>("N");
        right = new BinaryTree<>("M");
        binaryTree.getRightTree().setLeftTree(left);
        binaryTree.getRightTree().setRightTree(right);

        left = new BinaryTree<>("S");
        right = new BinaryTree<>("U");
        binaryTree.getLeftTree().getLeftTree().setLeftTree(left);
        binaryTree.getLeftTree().getLeftTree().setRightTree(right);

        left = new BinaryTree<>("R");
        right = new BinaryTree<>("W");
        binaryTree.getLeftTree().getRightTree().setLeftTree(left);
        binaryTree.getLeftTree().getRightTree().setRightTree(right);

        left = new BinaryTree<>("D");
        right = new BinaryTree<>("K");
        binaryTree.getRightTree().getLeftTree().setLeftTree(left);
        binaryTree.getRightTree().getLeftTree().setRightTree(right);

        left = new BinaryTree<>("G");
        right = new BinaryTree<>("O");
        binaryTree.getRightTree().getRightTree().setLeftTree(left);
        binaryTree.getRightTree().getRightTree().setRightTree(right);

        left = new BinaryTree<>("H");
        right = new BinaryTree<>("V");
        binaryTree.getLeftTree().getLeftTree().getLeftTree().setLeftTree(left);
        binaryTree.getLeftTree().getLeftTree().getLeftTree().setRightTree(right);

        left = new BinaryTree<>("F");
        binaryTree.getLeftTree().getLeftTree().getRightTree().setLeftTree(left);
        binaryTree.getLeftTree().getLeftTree().getLeftTree().setRightTree(null);

        left = new BinaryTree<>("L");
        binaryTree.getLeftTree().getRightTree().getLeftTree().setLeftTree(left);
        binaryTree.getLeftTree().getRightTree().getLeftTree().setRightTree(null);

        left = new BinaryTree<>("P");
        right = new BinaryTree<>("J");
        binaryTree.getLeftTree().getRightTree().getRightTree().setLeftTree(left);
        binaryTree.getLeftTree().getRightTree().getRightTree().setRightTree(right);

        left = new BinaryTree<>("B");
        right = new BinaryTree<>("X");
        binaryTree.getRightTree().getLeftTree().getLeftTree().setLeftTree(left);
        binaryTree.getRightTree().getLeftTree().getLeftTree().setRightTree(right);

        left = new BinaryTree<>("C");
        right = new BinaryTree<>("Y");
        binaryTree.getRightTree().getLeftTree().getRightTree().setLeftTree(left);
        binaryTree.getRightTree().getLeftTree().getRightTree().setRightTree(right);

        left = new BinaryTree<>("B");
        right = new BinaryTree<>("X");
        binaryTree.getRightTree().getLeftTree().getLeftTree().setLeftTree(left);
        binaryTree.getRightTree().getLeftTree().getLeftTree().setRightTree(right);

        left = new BinaryTree<>("Z");
        right = new BinaryTree<>("Q");
        binaryTree.getRightTree().getRightTree().getLeftTree().setLeftTree(left);
        binaryTree.getRightTree().getRightTree().getLeftTree().setRightTree(right);
    }


    /**
     * Der Baum wird im übergebenem Panel dargestellt.
     * Dazu wird zunächst die alte Zeichnung entfernt.
     * Im Anschluss wird eine eine internete Hilfsmethode aufgerufen.
     *
     * @param panel Das DrawingPanel-Objekt, auf dem gezeichnet wird.
     */
    public void showTree(DrawingPanel panel){
        panel.removeAllObjects();
        //Der Baum wird in der Mitte des Panels beginnend gezeichnet: x = panel.getWidth()/2
        //Der linke und rechte Knoten in Tiefe 1 sind jeweils ein Viertel der Breite des Panels entfernt: spaceToTheSide = panel.getWidth()/4
        showTree(binaryTree, panel, panel.getWidth() / 2, 50, panel.getWidth() / 3);

        //Aufruf fordert das Panel zur Aktualisierung auf.
        panel.repaint();
    }

    /**
     * Hilfsmethode zum Zeichnen des Baums.
     * Für jeden Knoten wird ein neues TreeNode-Objekt dem panel hinzugefügt.
     * Für jede Kante wird ein neues TreePath-Pbjekt dem panel hinzugefügt.
     *
     * @param tree           Der zu zeichnende (Teil)Binärbaum.
     * @param panel          Das DrawingPanel-Objekt, auf dem gezeichnet wird.
     * @param startX         x-Koordinate seiner Wurzel
     * @param startY         y-Koordinate seiner Wurzel
     * @param spaceToTheSide Gibt an, wie weit horizontal entfernt die folgenden Bäume gezeichnet werden soll.
     */
    private void showTree(BinaryTree<String> tree, DrawingPanel panel, double startX, double startY, double spaceToTheSide){
        int radius = 9;
        if (!tree.isEmpty()){
            panel.addObject(new TreeNode(startX, startY, radius,  tree.getContent(), true));        //DER RADUIS WIRD IRGENDWIE DOPPELT ANGEWENDET...WTF HABEN SIE DA GEMACHT??? ICH KANN SO NICHT ARBEITEN!
            if(!tree.getLeftTree().isEmpty()){
                panel.addObject(new TreePath(startX, startY + radius*2,startX - (spaceToTheSide /2), startY + 80 - radius*2,0, true ));
            }
            if(!tree.getRightTree().isEmpty()){
                panel.addObject(new TreePath(startX, startY + radius*2,startX + (spaceToTheSide /2), startY + 80 - radius*2,0, true ));
            }
            showTree(tree.getLeftTree(), panel, startX - (spaceToTheSide /2), startY + 80,spaceToTheSide / 2);
            showTree(tree.getRightTree(), panel, startX + (spaceToTheSide /2), startY + 80,spaceToTheSide / 2);
        }


    }

    /**
     * Es wird das Ergebnis einer Traversierung bestimmt.
     * Ruft dazu eine interne Hilfsmethode auf.
     *
     * @return Das Ergebnis der Traversierung als Zeichenkette.
     */
    public String traverse(){
        return traverse(binaryTree);
    }

    /**
     * Interne hilfsmethode zur Traversierung.
     *
     * @param tree Der zu traversierende Binärbaum.
     * @return Das Ergebnis der Traversierung als Zeichenkette.
     */
    private String traverse(BinaryTree tree){
        //TODO 04: Nachdem wir geklärt haben, was eine Traversierung ist, muss diese Methode noch vervollständigt werden. Sollte ein Kinderspiel sein.
        if(!tree.isEmpty()){
            return "" + tree.getContent() + traverse(tree.getLeftTree()) + traverse(tree.getRightTree());
        }
        return "";
    }

    /**
     * Interne Übungsmethode zur Traversierung.
     *
     * @param tree Der zu traversierende Binärbaum.
     * @return Die Anzahl der Knoten in diesem Baum
     */
    private int countNodes(BinaryTree tree){
        //TODO 05: Übungsmethode
        return 0;
    }
}
