/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytools;

import com.excel.ExcelOperationUtil;
import com.model.GetApp;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

/**
 *
 * @author wpskl
 *
 */
public class GetFilePathSingle {

    public void getPanel(JPanel jp) {
        //JPanel jp = new JPanel();
        JTextArea fileTarget = new JTextArea(200, 300);
        fileTarget.setDragEnabled(true);
        fileTarget.setTransferHandler(new TransferHandler() {
            private static final long serialVersionUID = 1L;

            public boolean importData(JComponent c, Transferable t) {
                try {
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                    //此处输出文件/文件夹的名字以及路径
                    String s=new String(o.toString().substring(0,o.toString().length() - 1));
                    System.out.println(s);
                    List<GetApp> list = new ArrayList<GetApp>();
                    list=new ExcelOperationUtil().readExcelData(s);
                    return true;
                } catch (UnsupportedFlavorException ufe) {
                    ufe.printStackTrace();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            public boolean canImport(JComponent c, DataFlavor[] flavors) {
                return true;
            }
        });
        jp.add(fileTarget);
        //return jp;
    }


}
