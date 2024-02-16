package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.controller.MouseEventDemo;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private MyMenyBar menu;
    private MyToolBar toolBar;
    private ClassyTree classyTree;
    private ClassyTreeView jTreeProjectExplorer;
    private PackageView desktopPanel;
    private MyGraphicToolbar graphicToolBar;

    private JPanel desktop;

    private MainFrame(){

    }

    private void initialise(){
        actionManager = new ActionManager();
        classyTree = new ClassyTreeImplementation();
        ProjectExplorer pe= ApplicationFramework.getInstance().getClassyRepository().getProjectExplorer();
        jTreeProjectExplorer = classyTree.generateTree(pe);
        pe.addSubscriber(jTreeProjectExplorer);
        jTreeProjectExplorer.addMouseListener(new MouseEventDemo(jTreeProjectExplorer));
        initialiseGUI(jTreeProjectExplorer);
    }
    private void initialiseGUI(ClassyTreeView projectExplorer){


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        menu = new MyMenyBar();
        setJMenuBar(menu);

        toolBar = new MyToolBar();
        add(toolBar,BorderLayout.NORTH);

        desktopPanel = new PackageView();
        desktop = new JPanel();
        desktop.setLayout(new BorderLayout());
        desktop.add(desktopPanel, BorderLayout.NORTH);

        projectExplorer.addTreeSelectionListener(desktopPanel);


        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktop);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        graphicToolBar = new MyGraphicToolbar();
        getContentPane().add(graphicToolBar, BorderLayout.EAST);


    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialise();

        }
        return instance;
    }


    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void update(Object notification) {

    }
    public PackageView getDesktopPanel(){return desktopPanel;}
}


