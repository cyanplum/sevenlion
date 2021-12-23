package cn.sevenlion.common.mapstruct.convert;//package cn.sevenlion.utils.mapstruct.convert;
//
//import cn.sevenlion.utils.oss.model.FileInfoVo;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author: qimeiwen
// * @create: 2021-12-06
// */
//public class MapperDecorator {
//
//    public FileInfoVo str2FileInfoVo(String databaseName) {
//        return OssUtils.getFileInfoResult(databaseName);
//    }
//
//    public String fileInfo2File(FileInfoVo fileInfoVo) {
//        return fileInfoVo.getStoreName();
//    }
//
//    public List<FileInfoVo> str2FileInfoVo(List<String> databaseName) {
//        List<FileInfoVo> list = new ArrayList<>();
//        for (String name : databaseName) {
//            list.add(OssUtils.getFileInfoResult(name));
//        }
//        return list;
//    }
//
//    public List<String> fileInfo2File(List<FileInfoVo> fileInfoVos) {
//        return fileInfoVos.stream().map(FileInfoVo::getStoreName).collect(Collectors.toList());
//    }
//}
