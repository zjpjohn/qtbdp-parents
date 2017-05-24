package com.qtdbp.tradingadmin.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qtdbp.poi.excel.AbstractExcel2007Writer;
import com.qtdbp.poi.excel.Excel2007Writer;
import com.qtdbp.poi.excel.model.ExcelSheetModel;
import com.qtdbp.poi.excel.service.IExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * POI文件处理类
 *
 * @author: caidchen
 * @create: 2017-05-23 15:35
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PoiParserService implements IExcelReader {

    private List<String> files ;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public void getSheet(ExcelSheetModel excelSheetModel) {

        if(files == null) files = new ArrayList<>();

        ByteArrayOutputStream outStream = null ;
        try {
            outStream = new ByteArrayOutputStream();

            AbstractExcel2007Writer excel07Writer = new Excel2007Writer();
            excel07Writer.process(outStream, excelSheetModel);

            StorePath storePath = storageClient.uploadFile(new ByteArrayInputStream(outStream.toByteArray()) , outStream.toByteArray().length, "xlsx", null) ;
            files.add(storePath.getFullPath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(outStream != null) outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
