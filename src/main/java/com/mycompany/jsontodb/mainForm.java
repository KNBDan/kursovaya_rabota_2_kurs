package com.mycompany.jsontodb;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Dialog;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.ListModel;
import javax.swing.UIManager;
import org.json.JSONArray;
import org.json.JSONException;
import javax.swing.JOptionPane;

public class mainForm extends javax.swing.JFrame {

    DefaultTreeModel treeModel; //можель дерева с базами данных
    Connection connection = null;
    JOptionPane optionPane = new JOptionPane(); //информативное окно для оповещения пользователя

    String defaultSavePlace = "C:\\Users\\HPPavilion\\Desktop"; //стандартный путь к сохранению
    //Информация для занесения в json
    private String urlS;
    private String userS;
    private String passwordS;
    private String catalogS;
    private String schemaS;
    private File selectedFileNow; //фйал для занесения в бд
    private Map<Integer, String> curFullSaveMap;

    public mainForm() {
        try { //изменение дизайна окон
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        initTree(); //создаем дерево больше как тест        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        directoryChooser = new javax.swing.JFileChooser();
        enterDBInfo = new javax.swing.JFrame();
        urlT = new javax.swing.JLabel();
        userT = new javax.swing.JLabel();
        pasT = new javax.swing.JLabel();
        urlEnter = new javax.swing.JTextField();
        userEnter = new javax.swing.JTextField();
        passwordEnter = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        enabledDbBox = new javax.swing.JComboBox<>();
        saveTableD = new javax.swing.JDialog();
        savings = new javax.swing.JScrollPane();
        saveList = new javax.swing.JList<>();
        catalogNameLable = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        resetBut = new javax.swing.JButton();
        isActualWay = new javax.swing.JCheckBox();
        savePlaseField = new javax.swing.JTextField();
        choiseSavePlaceBut = new javax.swing.JButton();
        convertButton = new javax.swing.JButton();
        saveName = new javax.swing.JCheckBox();
        fileNameField = new javax.swing.JTextField();
        catalogNameLable1 = new javax.swing.JLabel();
        createMaket = new javax.swing.JDialog();
        createBut = new javax.swing.JButton();
        testTF = new javax.swing.JTextField();
        labelCreateSchema = new javax.swing.JLabel();
        labelShcemaName = new javax.swing.JLabel();
        dublicateError = new javax.swing.JDialog();
        createOther = new javax.swing.JButton();
        backToEnter = new javax.swing.JButton();
        reWrite = new javax.swing.JButton();
        nameSaveLab1 = new javax.swing.JLabel();
        enterSaveName = new javax.swing.JDialog();
        nameSaveLab = new javax.swing.JLabel();
        saveLabel = new javax.swing.JTextField();
        createSaveButton = new javax.swing.JButton();
        scrolJson = new javax.swing.JScrollPane();
        listJson = new javax.swing.JList<>();
        scrolDb = new javax.swing.JScrollPane();
        dbTree = new javax.swing.JTree();
        buttonToJson = new javax.swing.JButton();
        dbLabel = new javax.swing.JLabel();
        jsonLabel = new javax.swing.JLabel();
        convertToDb = new javax.swing.JButton();
        createSchemaBut = new javax.swing.JButton();
        checkInfo = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newConnection = new javax.swing.JMenuItem();
        selectMenu = new javax.swing.JMenuItem();
        selectDefauldFolderForSave = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        directoryChooser.setApproveButtonToolTipText("");
        directoryChooser.setBackground(java.awt.Color.white);
        directoryChooser.setDialogTitle("");
        directoryChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        directoryChooser.setSelectedFiles(null);
        directoryChooser.getAccessibleContext().setAccessibleName("");

        enterDBInfo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        enterDBInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        enterDBInfo.setResizable(false);
        enterDBInfo.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                enterDBInfoWindowClosed(evt);
            }
        });

        urlT.setText("url");

        userT.setText("user");

        pasT.setText("password");

        urlEnter.setText("jdbc:mysql://localhost:3306");
        urlEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlEnterActionPerformed(evt);
            }
        });

        userEnter.setText("root");
        userEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEnterActionPerformed(evt);
            }
        });

        passwordEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordEnterActionPerformed(evt);
            }
        });

        connectButton.setText("Try connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        enabledDbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mySQL", "postgre", "MCSQL" }));
        enabledDbBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enabledDbBoxItemStateChanged(evt);
            }
        });
        enabledDbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enabledDbBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enterDBInfoLayout = new javax.swing.GroupLayout(enterDBInfo.getContentPane());
        enterDBInfo.getContentPane().setLayout(enterDBInfoLayout);
        enterDBInfoLayout.setHorizontalGroup(
            enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterDBInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(enterDBInfoLayout.createSequentialGroup()
                        .addComponent(enabledDbBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(enterDBInfoLayout.createSequentialGroup()
                        .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(enterDBInfoLayout.createSequentialGroup()
                                .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pasT, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(passwordEnter, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(userEnter))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(enterDBInfoLayout.createSequentialGroup()
                                .addComponent(urlT, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(urlEnter)))
                        .addGap(6, 6, 6))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enterDBInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(connectButton)
                .addContainerGap())
        );
        enterDBInfoLayout.setVerticalGroup(
            enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterDBInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enabledDbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlT)
                    .addComponent(urlEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(enterDBInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pasT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(connectButton)
                .addContainerGap())
        );

        saveTableD.setResizable(false);

        saveList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                saveListValueChanged(evt);
            }
        });
        savings.setViewportView(saveList);

        catalogNameLable.setText("Каталог:");

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchButton.setText("поиск");
        searchButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        resetBut.setText("сброс");
        resetBut.setMargin(new java.awt.Insets(1, 1, 1, 1));
        resetBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButActionPerformed(evt);
            }
        });

        isActualWay.setSelected(true);
        isActualWay.setText("Шаблон сохранения");
        isActualWay.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                isActualWayStateChanged(evt);
            }
        });

        choiseSavePlaceBut.setText("выбрать");
        choiseSavePlaceBut.setMargin(new java.awt.Insets(1, 1, 1, 1));
        choiseSavePlaceBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiseSavePlaceButActionPerformed(evt);
            }
        });

        convertButton.setText("Конвертировать");
        convertButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        convertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertButtonActionPerformed(evt);
            }
        });

        saveName.setSelected(true);
        saveName.setText("Сохранить имя");
        saveName.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                saveNameStateChanged(evt);
            }
        });
        saveName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNameActionPerformed(evt);
            }
        });

        catalogNameLable1.setText("Имя создаваемого файла");

        javax.swing.GroupLayout saveTableDLayout = new javax.swing.GroupLayout(saveTableD.getContentPane());
        saveTableD.getContentPane().setLayout(saveTableDLayout);
        saveTableDLayout.setHorizontalGroup(
            saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveTableDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(saveTableDLayout.createSequentialGroup()
                        .addComponent(savePlaseField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choiseSavePlaceBut))
                    .addGroup(saveTableDLayout.createSequentialGroup()
                        .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(saveTableDLayout.createSequentialGroup()
                                .addComponent(isActualWay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveName))
                            .addGroup(saveTableDLayout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetBut))
                            .addComponent(savings, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catalogNameLable, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(saveTableDLayout.createSequentialGroup()
                        .addComponent(catalogNameLable1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameField)))
                .addContainerGap())
            .addGroup(saveTableDLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(convertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        saveTableDLayout.setVerticalGroup(
            saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveTableDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(catalogNameLable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isActualWay)
                    .addComponent(saveName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savePlaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choiseSavePlaceBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(saveTableDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catalogNameLable1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(convertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        createBut.setText("Создать макет");
        createBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButActionPerformed(evt);
            }
        });

        testTF.setText("new_schema");

        labelCreateSchema.setText("Создать новую схему");

        labelShcemaName.setText("Имя:");

        javax.swing.GroupLayout createMaketLayout = new javax.swing.GroupLayout(createMaket.getContentPane());
        createMaket.getContentPane().setLayout(createMaketLayout);
        createMaketLayout.setHorizontalGroup(
            createMaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createMaketLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createMaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(createBut, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(createMaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCreateSchema, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(createMaketLayout.createSequentialGroup()
                            .addComponent(labelShcemaName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(testTF, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        createMaketLayout.setVerticalGroup(
            createMaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createMaketLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCreateSchema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(createMaketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(testTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelShcemaName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createBut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        createOther.setBackground(new java.awt.Color(204, 255, 204));
        createOther.setText("Создать другое");
        createOther.setMargin(new java.awt.Insets(1, 1, 1, 1));
        createOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOtherActionPerformed(evt);
            }
        });

        backToEnter.setBackground(new java.awt.Color(255, 153, 153));
        backToEnter.setText("Назад");
        backToEnter.setMargin(new java.awt.Insets(1, 1, 1, 1));
        backToEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToEnterActionPerformed(evt);
            }
        });

        reWrite.setBackground(new java.awt.Color(255, 255, 204));
        reWrite.setText("Перезаписать");
        reWrite.setMargin(new java.awt.Insets(1, 1, 1, 1));
        reWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reWriteActionPerformed(evt);
            }
        });

        nameSaveLab1.setText("Сохранение с таким названием уже существует");

        javax.swing.GroupLayout dublicateErrorLayout = new javax.swing.GroupLayout(dublicateError.getContentPane());
        dublicateError.getContentPane().setLayout(dublicateErrorLayout);
        dublicateErrorLayout.setHorizontalGroup(
            dublicateErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dublicateErrorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dublicateErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameSaveLab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dublicateErrorLayout.createSequentialGroup()
                        .addComponent(backToEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createOther, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        dublicateErrorLayout.setVerticalGroup(
            dublicateErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(dublicateErrorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameSaveLab1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dublicateErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backToEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createOther, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createOther.getAccessibleContext().setAccessibleDescription("");

        nameSaveLab.setText("Название сохранения:");

        createSaveButton.setText("Создать");
        createSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enterSaveNameLayout = new javax.swing.GroupLayout(enterSaveName.getContentPane());
        enterSaveName.getContentPane().setLayout(enterSaveNameLayout);
        enterSaveNameLayout.setHorizontalGroup(
            enterSaveNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterSaveNameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(enterSaveNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(enterSaveNameLayout.createSequentialGroup()
                        .addComponent(nameSaveLab)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(saveLabel))
                .addContainerGap())
            .addGroup(enterSaveNameLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(createSaveButton)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        enterSaveNameLayout.setVerticalGroup(
            enterSaveNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterSaveNameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameSaveLab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createSaveButton)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        listJson.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listJson.setToolTipText("");
        scrolJson.setViewportView(listJson);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        dbTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        dbTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                dbTreeValueChanged(evt);
            }
        });
        scrolDb.setViewportView(dbTree);

        buttonToJson.setText("<<<Из базы данных в JSON<<<");
        buttonToJson.setEnabled(false);
        buttonToJson.setMargin(new java.awt.Insets(1, 10, 1, 10));
        buttonToJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonToJsonActionPerformed(evt);
            }
        });

        dbLabel.setText("DataBases");

        jsonLabel.setText("Files Json");

        convertToDb.setText(">>>Из JSON в базу данных>>>");
        convertToDb.setEnabled(false);
        convertToDb.setMargin(new java.awt.Insets(1, 10, 1, 10));
        convertToDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertToDbActionPerformed(evt);
            }
        });

        createSchemaBut.setText("Создать новый макет");
        createSchemaBut.setEnabled(false);
        createSchemaBut.setMargin(new java.awt.Insets(1, 10, 1, 10));
        createSchemaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSchemaButActionPerformed(evt);
            }
        });

        checkInfo.setText("Выберите схему");

        fileMenu.setText("Файл");

        newConnection.setText("Добавить подключение");
        newConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newConnectionActionPerformed(evt);
            }
        });
        fileMenu.add(newConnection);

        selectMenu.setText("Выбрать путь к JSON");
        selectMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMenuActionPerformed(evt);
            }
        });
        fileMenu.add(selectMenu);

        selectDefauldFolderForSave.setText("Задать путь для стандартного сохранения");
        selectDefauldFolderForSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDefauldFolderForSaveActionPerformed(evt);
            }
        });
        fileMenu.add(selectDefauldFolderForSave);

        menuBar.add(fileMenu);

        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrolJson, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(convertToDb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonToJson, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(createSchemaBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrolDb, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dbLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrolDb, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jsonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(convertToDb, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonToJson, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(createSchemaBut, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 180, Short.MAX_VALUE))
                            .addComponent(scrolJson, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonToJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonToJsonActionPerformed
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        //ветвь обязательно путь к схеме
        String schemaM = null;
        String catalogM = null;
        while (selectedNode.getParent().toString() != "Root") {
            schemaM = catalogM;
            catalogM = selectedNode.toString();
            selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
        }
        String[] folderParts = selectedNode.toString().split(" "); // Разбиваем строку по пробелам
        String typeDB = folderParts[0]; // бд
        String user = folderParts[2]; // user
        String[] dataDB = localUserInfo.getConnectionInfoByTypeUser(user, typeDB);
        if (dataDB == null) {
            optionPane.setMessage("Ошибка в обращении к данным");
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog(null, "");
            dialog.setVisible(true);
            return;
        }
        System.out.println(dataDB[0]);
        System.out.println(catalogM);
        urlS = dataDB[0];
        userS = dataDB[1];
        passwordS = dataDB[2];
        catalogS = catalogM;
        schemaS = schemaM;
        //лист со всеми сохранениями в бд
        curFullSaveMap = unicDataQuery.getSaveMap(urlS, userS, passwordS, catalogS, schemaS);
        insertIntoSaveList(curFullSaveMap);
        if (schemaM == null) {
            catalogNameLable.setText("Текущая схема: " + catalogM);
        } else {
            catalogNameLable.setText("Текущая схема: " + schemaM);
        }
        //обновляю путьт в зависимости от того, является ли переключатель вкл
        if (isActualWay.isSelected() == true) {
            savePlaseField.setText(defaultSavePlace);
            savePlaseField.setEditable(false);
            choiseSavePlaceBut.setEnabled(false);
        } else {
            savePlaseField.setEditable(true);
            choiseSavePlaceBut.setEnabled(true);
        }
        saveName.setSelected(true);
        fileNameField.setText("");
        //вывожу окошко с выводом всех данных из таблицы savings
        //выбор папки для сохранения (возможно в файл стоит сохранить базувую папку для сохранения)
        //после выбора нужного осхранения могу выводить количество фигур в нём. как доп инфу малым шрифтом.
        //после нажатия ок создаю файл с таким же названием ИЛИ добавить галочку на сохранении текущего названия
        //те вывожу окно с запросом на написание названия файла

        //вызов окна сохранения
        saveTableD.setDefaultCloseOperation(saveTableD.DISPOSE_ON_CLOSE);
        saveTableD.pack();
        saveTableD.setModal(true);
        saveTableD.setLocationRelativeTo(this);
        saveTableD.setVisible(true);

    }//GEN-LAST:event_buttonToJsonActionPerformed
    private void insertIntoSaveList(Map<Integer, String> idNameMap) {
        // Создаем модель списка
        DefaultListModel<String> listModel = new DefaultListModel<>();
        // Добавляем элементы из Map в модель списка
        idNameMap.forEach((id, name) -> listModel.addElement("id: " + id + "| " + name));
        // Устанавливаем модель списка в JList
        saveList.setModel(listModel);
    }

    //выбор папки с json файлами
    File dirJsonFiles = null; //Папка с выбранными jsono файлами
    private void selectMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMenuActionPerformed
        int result = directoryChooser.showDialog(this, "Choose");
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = directoryChooser.getSelectedFile();
            System.out.println("Selected Folder: " + selectedFolder.getAbsolutePath());
            //Используем выбранную папку и фильтр для поиска файлов с расширением .json
            File[] jsonFiles = selectedFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
            if (jsonFiles != null && jsonFiles.length > 0) {
                listJson.removeAll();//Очищаем модель перед добавлением новых элементов
                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (File jsonFile : jsonFiles) {
                    System.out.println("Found JSON File: " + jsonFile.getAbsolutePath());
                    listModel.addElement(jsonFile.getName());
                }
                listJson.setModel(listModel);
                dirJsonFiles = selectedFolder; //Сохранение папки с Json Файлами, если в ней присутсвуют таковые
            } else {
                System.out.println("No JSON files found in the selected folder.");
            }
        } else {
            System.out.println("No folder selected");
        }
    }//GEN-LAST:event_selectMenuActionPerformed
    //Функция возвращающая путь до выбранной папки
    public String getDefWay() {
        // Создание диалогового окна выбора файла
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите папку");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //Отображение диалогового окна выбора файла
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Получение выбранного файла
            File selectedFolder = fileChooser.getSelectedFile();
            // Получение пути к выбранной папке
            String selectedFolderPath = selectedFolder.getAbsolutePath();

            // Вывод пути к выбранной папке
            return selectedFolderPath;

            // Теперь вы можете использовать переменную selectedFolderPath для дальнейших операций
        } else {
            return null;
        }
    }


    //Попытка заполучить всех таблицы из бд
    private void dbTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_dbTreeValueChanged
        checkInfo.setText("");
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
            String selectedCatalog = (String) selectedNode.toString(); //каталог с схемами (для mysql с таблицами)
            //String parent = selectedNode.getParent().toString(); //её родитель для правильного подключения
            //System.out.println(parent);
            //if ("Root".equals(parent)){ //Выбрали просто папку субд и сообщили пользователю
            if (selectedNode.toString().contains("postgre user:") || selectedNode.toString().contains("mysql user") || selectedNode.getParent().toString().contains("postgre user:")) {
                checkInfo.setText("Выберите конкретную схему!!");
                checkInfo.setForeground(Color.red);
                createSchemaBut.setEnabled(true);
                convertToDb.setEnabled(false);
                buttonToJson.setEnabled(false);
                if (selectedNode.toString().contains("postgre user:")) {
                    createSchemaBut.setEnabled(false);
                }

                return;
            }

            //активация кнопок проверки и созданяи своей схемы
            isGoodForEnter();
            createSchemaBut.setEnabled(true);
            /*
            String[] folderParts = parent.split(" "); // Разбиваем строку по пробелам
            if (folderParts.length == 1){
                folderParts = selectedNode.getParent().getParent().toString().split(" ");
            }
            String typeDB = folderParts[0]; // бд
            String user = folderParts[2]; // user
            String[] dataDB = localUserInfo.getConnectionInfoByTypeUser(user, typeDB);
            if (dataDB==null){
                System.out.println("error: ошибка в изимании данных из файла");
                return;
            }
            System.out.println(dataDB[0]);
            System.out.println(selectedCatalog);
             *///возможно пригодится для тестов

            //старое и непонятное
            /*
            // Получение списка таблиц для выбранной базы данных !!!!!!! 
             ArrayList<String> tables = UnicDataInsert.getTables(dataDB[0], dataDB[1],dataDB[2],selectedCatalog,null);
            // Заполнение JComboBox списком таблиц
            tableFigBox.removeAllItems();
            for (String table : tables) {
                tableFigBox.addItem(table);
            }*/
        }
    }//GEN-LAST:event_dbTreeValueChanged

    //кнопка добавления нового коннекта к базе данных(конкретно к субд)
    private void newConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newConnectionActionPerformed
        this.setEnabled(false); //Блокируем основное окно

        enterDBInfo.setDefaultCloseOperation(enterDBInfo.DISPOSE_ON_CLOSE);
        enterDBInfo.pack();
        enterDBInfo.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        enterDBInfo.setLocationRelativeTo(this); //забираем доступ у основного окна
        enterDBInfo.setVisible(true);

    }//GEN-LAST:event_newConnectionActionPerformed

    //слушатель при закрытии окна ввода инфы о бд
    private void enterDBInfoWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_enterDBInfoWindowClosed
        this.setEnabled(true); //возвращаем доступ к основному окну
        this.toFront();
    }//GEN-LAST:event_enterDBInfoWindowClosed

    String dbNowCon = "mysql";//тип драйвера для подключения с базовым значением

    //Добавление дерева с бд в окошко
    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        enabledDbBox.setBackground(Color.white);
        if (dbNowCon == "") { //если не выбранна бд для подключения
            enabledDbBox.setBackground(Color.red);
        } else { //если бд выбрана, то подключаемся и закрываем окно
            String user = userEnter.getText();
            String url = urlEnter.getText();
            String password = passwordEnter.getText();
            try {
                connection = DataBaseConnection.addConnection(connection, url, user, password); //добавляем субд и его базы данных в дерево //ПОКа функция лишь длобавляет в дерево. подключение будет в другом мест  
                if (localUserInfo.addConnectInfo(user, url, password)) {
                    //Если таких данных в файле нет, то создаем новую запись и обновляем дерево
                    addTreeNewDb(url, user, password);
                    enterDBInfo.dispose();
                    optionPane.setMessage("Запись успешно создана");
                    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //Если запись уже существует и/или произошла ошибка
                    optionPane.setMessage("Запись не создана:\nзапись уже существует");
                    optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
                }
            } catch (ClassNotFoundException | SQLException e) {//Попытка проинформировать пользвоателя
                String erM = e.getMessage();
                if (erM.contains("password")) { // Обработка исключения, возникающего при неправильном логине/пароле или URL    
                    optionPane.setMessage("Ошибка подключения:\nНеверный логин и/или пароль ");
                    optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                } else if (erM.contains("host")) {   //Обработка исключения при неаерном хосте
                    optionPane.setMessage("Ошибка подключения:\nНеверный url ");
                    optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                } else {//Ошибки с ClassNotFoundException нельзя распознать в явном виде
                    optionPane.setMessage("Ошибка подключения:\nеизвестная ошибка");
                    optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                }
                e.printStackTrace();
            }
            if (connection != null) {

            } else {

            }

            JDialog dialog = optionPane.createDialog(enterDBInfo, "");
            dialog.setModal(true);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    //добавление в дерево новой бд
    public void addTreeNewDb(String url, String user, String password) {
        try {
            String driverName = "";
            if (url.contains("mysql")) {
                driverName = "mysql";
            } else if (url.contains("postgresql")) {
                driverName = "postgre";
            } else if (url.contains("mcsql")) {
                driverName = "mcsql";
            }
            // Получение метаданных баз данных
            DatabaseMetaData metaData = connection.getMetaData();
            // Получение результата с информацией о базах данных
            metaData.getURL().toString();
            // ПОЛУЧЕНИЕ КАТАЛОГОВ
            ResultSet resultSet = metaData.getCatalogs();
            // Вывод списка баз данных в дерево
            DefaultMutableTreeNode driverTree = new DefaultMutableTreeNode(driverName + " user: " + user); //создание папки субд
            while (resultSet.next()) {
                String catalogName = resultSet.getString("TABLE_CAT");
                // Создание узла для каталога
                DefaultMutableTreeNode catalogNode = new DefaultMutableTreeNode(catalogName);
                // Подключение к каталогу
                try {
                    Connection catalogConnection = unicDataQuery.connectToChosed(url, user, password, catalogName, null);
                    //DriverManager.getConnection(url + catalogName, user, "1234")
                    // Получение списка схем в каталоге
                    ResultSet schemas = catalogConnection.getMetaData().getSchemas();
                    while (schemas.next()) {
                        String schemaName = schemas.getString("TABLE_SCHEM");
                        // Создание узла для схемы
                        DefaultMutableTreeNode schemaNode = new DefaultMutableTreeNode(schemaName);
                        catalogNode.add(schemaNode);
                    }
                    catalogConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                driverTree.add(catalogNode);
            }
            root.add(driverTree);
            treeModel = (DefaultTreeModel) dbTree.getModel();
            treeModel.setRoot(root);
            dbTree.setModel(treeModel);   //перерисовывание дерева         
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //обновляем локальные url user о подключении
    private void enabledDbBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_enabledDbBoxItemStateChanged
        Object selected = enabledDbBox.getSelectedItem(); //обновляем базовые значения для выбранной субд
        if (selected.toString() == "mySQL") {
            urlEnter.setText("jdbc:mysql://localhost:3306");
            userEnter.setText("root");  //pas = sqlPassword2023   
//            dbNowCon = "mysql";
        } else if (selected.toString() == "postgre") {
            urlEnter.setText("jdbc:postgresql://localhost:5432/");
            userEnter.setText("postgres"); //pas = 1234       
//            dbNowCon = "postgre";
        } else if (selected.toString() == "MCSQL") {
            urlEnter.setText("jdbc:mcsql://localhost:3306");
            userEnter.setText("root");
//            dbNowCon = "mcsql";
        }
    }//GEN-LAST:event_enabledDbBoxItemStateChanged

    private void passwordEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordEnterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordEnterActionPerformed

    private void urlEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlEnterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_urlEnterActionPerformed

    private void userEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEnterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userEnterActionPerformed

    private void enabledDbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enabledDbBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enabledDbBoxActionPerformed
    private void isGoodForEnter() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
            String catalogM = null; //каталог
            String schemaM = null; //схема
            while (selectedNode.getParent().toString() != "Root") { //Для разных уровней, те для бд с схемами и без
                schemaM = catalogM;
                catalogM = selectedNode.toString();
                selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
            }
            String parent = selectedNode.toString();//Родитель для правильного подключения
            System.out.println(parent);
            String[] folderParts = parent.split(" "); // Разбиваем строку по пробелам
            String typeDB = folderParts[0]; // бд
            String user = folderParts[2]; // user
            String[] dataDB = localUserInfo.getConnectionInfoByTypeUser(user, typeDB);
            if (dataDB == null) {
                optionPane.setMessage("Ошибка в обращении к данным");
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(null, "");
                dialog.setVisible(true);
                return;
            }
            System.out.println(dataDB[0]);
            System.out.println(catalogM);
            // Получение списка таблиц для выбранной базы данных !!!!!!! 
            List<String> tablesCheck = unicDataQuery.getTableNames(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaM);
            //UnicDataInsert.countTables(dataDB[0], dataDB[1],dataDB[2],selectedCatalog,null) >/*изменить условие*/ 0 // Старое условие просто подсчет количества таблиц
            if (tablesCheck.contains("savings") && tablesCheck.contains("figures") && tablesCheck.contains("links")
                    && tablesCheck.contains("in_vars") && tablesCheck.contains("out_vars") && tablesCheck.contains("var_in_out_links")) {
                checkInfo.setText("Схема подходит для работы");
                checkInfo.setForeground(Color.green);
                convertToDb.setEnabled(true);
                buttonToJson.setEnabled(true);
            } else {
                checkInfo.setText("Схема НЕ подходит для работы");
                checkInfo.setForeground(Color.red);
                convertToDb.setEnabled(false);
                buttonToJson.setEnabled(false);
            }
        }
    }

   //кнопка создания каталога по шаблону для работы с программной и файлами
    private void createButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButActionPerformed
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
            String catalogM = null;
            String parent = null;
            while (true) {
                //если работаем с mysql
                if (selectedNode.toString().contains("mysql")) {
                    parent = selectedNode.toString();
                    break;
                } //работаем с postgre
                else {
                    if (selectedNode.getParent().toString().contains("postgre")) {
                        parent = selectedNode.getParent().toString();
                        catalogM = selectedNode.toString();
                        break;
                    }
                }
                selectedNode = (DefaultMutableTreeNode) selectedNode.getParent(); //идём в глубь
            }
//            String selectedCatalog = (String) selectedNode.toString(); //каталог с схемами (для mysql с таблицами)
//            String parent = selectedNode.getParent().toString(); //её родитель для правильного подключения
            System.out.println(parent);
            String[] folderParts = parent.split(" "); // Разбиваем строку по пробелам
            String typeDB = folderParts[0]; // бд 
            String user = folderParts[2]; // user
            String[] dataDB = localUserInfo.getConnectionInfoByTypeUser(user, typeDB);
            if (dataDB == null) {
                optionPane.setMessage("Ошибка в обращении к данным");
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(null, "");
                dialog.setVisible(true);
                return;
            }
            System.out.println(dataDB[0]);
            System.out.println(catalogM);
            String schemaName = testTF.getText();
            boolean bol = unicDataQuery.createShema(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaName);
            if (bol) {
                unicDataQuery.createTables(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaName);
                root.removeAllChildren();
                loadTreeInfo();
                createMaket.setVisible(false);
                optionPane.setMessage("Новая схема была добалена в базу данных.\nДерево обновлено");
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            } else {
                optionPane.setMessage("Ошибка при создании схемы.\nВозможно, схема с таким именем\nуже существует");
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
            }
            JDialog dialog = optionPane.createDialog(null, "");
            dialog.setVisible(true);

        }
    }//GEN-LAST:event_createButActionPerformed

    private void convertToDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertToDbActionPerformed
        Object selectedValue = listJson.getSelectedValue();
        if (selectedValue != null) {
            String selectedFileName = selectedValue.toString();
            System.out.println(dirJsonFiles.getAbsolutePath() + "\\" + selectedFileName);
            selectedFileNow = new File(dirJsonFiles.getAbsolutePath() + "/" + selectedFileName);
            String saveName = selectedFileName.substring(0, selectedFileName.length() - 5);
            saveLabel.setText(saveName);

            enterSaveName.setDefaultCloseOperation(enterSaveName.DISPOSE_ON_CLOSE);
            enterSaveName.pack();
            enterSaveName.setModal(true);
            enterSaveName.setLocationRelativeTo(this);
            enterSaveName.setVisible(true);
        } else {
            optionPane.setMessage("Ошибка.\nВыберите файл из списка");
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog(null, "");
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_convertToDbActionPerformed

    private void isActualWayStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_isActualWayStateChanged
        if (isActualWay.isSelected() == true) {
            savePlaseField.setText(defaultSavePlace);
            savePlaseField.setEditable(false);
            choiseSavePlaceBut.setEnabled(false);
        } else {
            savePlaseField.setEditable(true);
            choiseSavePlaceBut.setEnabled(true);
        }

    }//GEN-LAST:event_isActualWayStateChanged

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertButtonActionPerformed
        String selectedItem = saveList.getSelectedValue();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Выберите элемент из списка", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Получаем id из выбранного элемента
        int idIndex = selectedItem.indexOf(":") + 2; // Находим индекс начала id, учитывая символы ": " после "id"
        int nameIndex = selectedItem.indexOf("|"); // Находим индекс конца id, который является символом перед "|"
        // Извлекаем подстроку с id и преобразуем её в int
        int id = Integer.parseInt(selectedItem.substring(4, nameIndex));
        String saveWay = savePlaseField.getText();
        toJson.createFigListForJson(urlS, userS, passwordS, catalogS, schemaS, id, fileNameField.getText(),saveWay);
    }//GEN-LAST:event_convertButtonActionPerformed

    private void saveNameStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_saveNameStateChanged
        if (saveName.isSelected() == true) {
            String input = saveList.getSelectedValue();
            if (input!=null){
                int pipeIndex = input.indexOf('|');
                if (pipeIndex != -1) {
                input = input.substring(pipeIndex + 1).trim();
                }        
                fileNameField.setText(input);
            }
            fileNameField.setEditable(false);           
        } else {
            fileNameField.setEditable(true);   
        }
    }//GEN-LAST:event_saveNameStateChanged

    private void createSchemaButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSchemaButActionPerformed
        //вызов окна сохранения
        testTF.setText("new_schema");
        createMaket.setDefaultCloseOperation(createMaket.DISPOSE_ON_CLOSE);
        createMaket.pack();
        createMaket.setModal(true);
        createMaket.setLocationRelativeTo(this);
        createMaket.setVisible(true);

    }//GEN-LAST:event_createSchemaButActionPerformed
    //Функция для записи в бд уже имеющего сохранения под другим именем
    private void createOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOtherActionPerformed
        String saveName = saveLabel.getText();
        String helpName = saveName;

        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
            //определяем, есть ли каталоги и схемы в данной бд. 
            String catalogM = null; //каталог
            String schemaM = null; //схема
            while (selectedNode.getParent().toString() != "Root") { //Для разных уровней, те для бд с схемами и без
                schemaM = catalogM;
                catalogM = selectedNode.toString();
                selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
            }
            String parent = selectedNode.toString();//Родитель для правильного подключения
            System.out.println(parent);
            String[] folderParts = parent.split(" "); // Разбиваем строку по пробелам
            String typeDB = folderParts[0]; // бд 
            String user = folderParts[2]; // user
            String[] dataDB = localUserInfo.getConnectionInfoByTypeUser(user, typeDB);
            if (dataDB == null) {
                optionPane.setMessage("Ошибка в обращении к данным");
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(null, "");
                dialog.setVisible(true);
                return;
            }
            try {
                // Получение выбранного имени файла
                connection = unicDataQuery.connectToChosed(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaM);
            } catch (SQLException ex) {
                Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 1; i < 1000; i++) {
                boolean isFileExist = unicDataQuery.isSaveNameExist(connection, catalogM, schemaM, helpName);
                if (isFileExist == true) {
                    dublicateError.setDefaultCloseOperation(dublicateError.DISPOSE_ON_CLOSE);
                    dublicateError.pack();
                    dublicateError.setModal(true);
                    dublicateError.setLocationRelativeTo(this);
                    dublicateError.setVisible(true);
                    System.out.println("Элемент с названием " + helpName + " существует в таблице savings.");
                    helpName = saveName + "(" + Integer.toString(i) + ")";
                } else {
                    System.out.println("Элемент с названием " + helpName + " не существует в таблице savings.");
                    saveName = helpName;
                    unicDataQuery.getFigFromJson(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaM, selectedFileNow, saveName);
                    break;

                }
            }
        }
        dublicateError.setVisible(false);
        enterSaveName.setVisible(false);
    }//GEN-LAST:event_createOtherActionPerformed

    private void createSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSaveButtonActionPerformed
        addSaveInDb();
    }//GEN-LAST:event_createSaveButtonActionPerformed

    private void backToEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToEnterActionPerformed
        dublicateError.setVisible(false);
    }//GEN-LAST:event_backToEnterActionPerformed
    //функция перезаписи сохранения
    private void reWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reWriteActionPerformed
        String saveName = saveLabel.getText();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        String catalogM = null; //каталог
        String schemaM = null; //схема
        while (selectedNode.getParent().toString() != "Root") { //Для разных уровней, те для бд с схемами и без
            schemaM = catalogM;
            catalogM = selectedNode.toString();
            selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
        }
        String[] dataDB = getConInfo();
        try {
            // Получение выбранного имени файла
            connection = unicDataQuery.connectToChosed(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaM);
        } catch (SQLException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        unicDataQuery.deleteSave(connection, catalogM, schemaM, saveName);
        addSaveInDb();
        dublicateError.setVisible(false);
    }//GEN-LAST:event_reWriteActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed

    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String searchElement = searchField.getText();
        Map<Integer, String> searchedMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : curFullSaveMap.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue();
            if (key.contains(searchElement) || value.contains(searchElement)) {
                searchedMap.put(entry.getKey(), value);
            }
        }
        insertIntoSaveList(searchedMap);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void resetButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButActionPerformed
        insertIntoSaveList(curFullSaveMap);
    }//GEN-LAST:event_resetButActionPerformed

    private void selectDefauldFolderForSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDefauldFolderForSaveActionPerformed
        defaultSavePlace = getDefWay();
    }//GEN-LAST:event_selectDefauldFolderForSaveActionPerformed

    private void choiseSavePlaceButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiseSavePlaceButActionPerformed
        savePlaseField.setText(getDefWay());
    }//GEN-LAST:event_choiseSavePlaceButActionPerformed

    private void saveNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveNameActionPerformed

    private void saveListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_saveListValueChanged
        if (saveName.isSelected() == true) {
            String input = saveList.getSelectedValue();
            if (input!=null){
                int pipeIndex = input.indexOf('|');
                if (pipeIndex != -1) {
                    input = input.substring(pipeIndex + 1).trim();
                }        
                fileNameField.setText(input);
            }      
            fileNameField.setText(input);
        }
    }//GEN-LAST:event_saveListValueChanged

    //функци ядобавления в бд с проверкой на существование данного сохранения
    private void addSaveInDb() {
        String saveName = saveLabel.getText();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        String catalogM = null; //каталог
        String schemaM = null; //схема
        while (selectedNode.getParent().toString() != "Root") { //Для разных уровней, те для бд с схемами и без
            schemaM = catalogM;
            catalogM = selectedNode.toString();
            selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
        }
        String[] dataDB = getConInfo();
        try {
            // Получение выбранного имени файла
            connection = unicDataQuery.connectToChosed(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaM);
        } catch (SQLException ex) {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean isFileExist = unicDataQuery.isSaveNameExist(connection, catalogM, schemaM, saveName);
        if (isFileExist == true) {
            dublicateError.setDefaultCloseOperation(dublicateError.DISPOSE_ON_CLOSE);
            dublicateError.pack();
            dublicateError.setModal(true);
            dublicateError.setLocationRelativeTo(this);
            dublicateError.setVisible(true);

        } else {
            System.out.println("Элемент с названием " + saveName + " не существует в таблице savings.");
            unicDataQuery.getFigFromJson(dataDB[0], dataDB[1], dataDB[2], catalogM, schemaM, selectedFileNow, saveName);
            enterSaveName.setVisible(false);
        }
    }

    //Функция получения информации о подключении
    private String[] getConInfo() {
        Connection conn = null;
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) dbTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getUserObject() instanceof String) {
            while (selectedNode.getParent().toString() != "Root") { //Для разных уровней, те для бд с схемами и без
                selectedNode = (DefaultMutableTreeNode) selectedNode.getParent();
            }
            String parent = selectedNode.toString();//Родитель для правильного подключения
            System.out.println(parent);
            String[] folderParts = parent.split(" "); // Разбиваем строку по пробелам
            String typeDB = folderParts[0]; // бд 
            String user = folderParts[2]; // user
            String[] dataDB = localUserInfo.getConnectionInfoByTypeUser(user, typeDB);
            if (dataDB == null) {
                optionPane.setMessage("Ошибка в обращении к данным");
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(null, "");
                dialog.setVisible(true);
                return null;
            }
            return dataDB;
        }
        return null;
    }

    // Функция подсчета количества фигур в выбранном файле
    private int countFigures(File file) {
        int figureCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            // Преобразование содержимого файла в массив объектов JSON
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            // Подсчет количества объектов в массиве
            figureCount = jsonArray.length();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return figureCount;
    }

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root"); //корень дерева

    //Фкнция заполнения дерева уже подключенными бд. Есть вопрос по поводу пароля, если его нельзя явно хранить, то добавить запрос пароля
    public void loadTreeInfo() {
        ArrayList<String[]> data = new ArrayList<>(); //список с данными о подключении
        data = localUserInfo.getInfo(connection);
        for (String[] info : data) {
            try { //попытка подключения и внесения в дерево
                connection = DataBaseConnection.addConnection(connection, info[0], info[1], info[2]);
                addTreeNewDb(info[0], info[1], info[2]);
            } catch (SQLException | ClassNotFoundException e) {
                Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    //инициализация дерева для вывода баз данных
    public void initTree() {
        treeModel = (DefaultTreeModel) dbTree.getModel(); //изымание текущей модели от дерева
        treeModel.setRoot(root); //изменение корня
        dbTree.setModel(treeModel); //установка модели в дерево
        dbTree.setRootVisible(false); //выключаем видимость корня root тк некрасиво
        loadTreeInfo(); //Внесение существующих подключений
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToEnter;
    private javax.swing.JButton buttonToJson;
    private javax.swing.JLabel catalogNameLable;
    private javax.swing.JLabel catalogNameLable1;
    private javax.swing.JLabel checkInfo;
    private javax.swing.JButton choiseSavePlaceBut;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton convertButton;
    private javax.swing.JButton convertToDb;
    private javax.swing.JButton createBut;
    private javax.swing.JDialog createMaket;
    private javax.swing.JButton createOther;
    private javax.swing.JButton createSaveButton;
    private javax.swing.JButton createSchemaBut;
    private javax.swing.JLabel dbLabel;
    private javax.swing.JTree dbTree;
    private javax.swing.JFileChooser directoryChooser;
    private javax.swing.JDialog dublicateError;
    private javax.swing.JComboBox<String> enabledDbBox;
    private javax.swing.JFrame enterDBInfo;
    private javax.swing.JDialog enterSaveName;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField fileNameField;
    private javax.swing.JCheckBox isActualWay;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel jsonLabel;
    private javax.swing.JLabel labelCreateSchema;
    private javax.swing.JLabel labelShcemaName;
    private javax.swing.JList<String> listJson;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nameSaveLab;
    private javax.swing.JLabel nameSaveLab1;
    private javax.swing.JMenuItem newConnection;
    private javax.swing.JLabel pasT;
    private javax.swing.JTextField passwordEnter;
    private javax.swing.JButton reWrite;
    private javax.swing.JButton resetBut;
    private javax.swing.JTextField saveLabel;
    private javax.swing.JList<String> saveList;
    private javax.swing.JCheckBox saveName;
    private javax.swing.JTextField savePlaseField;
    private javax.swing.JDialog saveTableD;
    private javax.swing.JScrollPane savings;
    private javax.swing.JScrollPane scrolDb;
    private javax.swing.JScrollPane scrolJson;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JMenuItem selectDefauldFolderForSave;
    private javax.swing.JMenuItem selectMenu;
    private javax.swing.JTextField testTF;
    private javax.swing.JTextField urlEnter;
    private javax.swing.JLabel urlT;
    private javax.swing.JTextField userEnter;
    private javax.swing.JLabel userT;
    // End of variables declaration//GEN-END:variables

}
