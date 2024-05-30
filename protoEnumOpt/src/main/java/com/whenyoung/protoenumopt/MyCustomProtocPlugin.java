package com.whenyoung.protoenumopt;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.compiler.PluginProtos;

import java.io.IOException;

public class MyCustomProtocPlugin  {

    public static void main(String[] args) throws IOException {
        System.out.println("MyCustomProtocPlugin start------------");
        // 从System.in读取CodeGeneratorRequest
        PluginProtos.CodeGeneratorRequest request = PluginProtos.CodeGeneratorRequest.parseFrom(System.in);

        // 处理.proto文件并生成代码
        PluginProtos.CodeGeneratorResponse.Builder responseBuilder = PluginProtos.CodeGeneratorResponse.newBuilder();
        process(request, responseBuilder);
        // 将CodeGeneratorResponse写入System.out
        PluginProtos.CodeGeneratorResponse response = responseBuilder.build();
        for (PluginProtos.CodeGeneratorResponse.File file : response.getFileList()) {
            System.out.println(file.getContent());
        }
        response.writeTo(System.out);
        System.out.println("\nMyCustomProtocPlugin end------------");
    }

    private static void process(PluginProtos.CodeGeneratorRequest request, PluginProtos.CodeGeneratorResponse.Builder responseBuilder) {
        // 在这里处理.proto文件并生成代码
        for (com.google.protobuf.DescriptorProtos.FileDescriptorProto file : request.getProtoFileList()) {
            for (DescriptorProtos.DescriptorProto message : file.getMessageTypeList()) {
                System.out.println("\nMessage name: " + message.getName());
                for (DescriptorProtos.FieldDescriptorProto fieldDescriptorProto : message.getFieldList()) {
                    System.out.println("\nMessage filed: " + fieldDescriptorProto.getName()+",type"+fieldDescriptorProto.getType().name());
                }
                String className = message.getName() + "Custom";
                String javaCode = "public class " + className + " {}";
                System.out.println("\njavaCode name: " + javaCode);

                PluginProtos.CodeGeneratorResponse.File.Builder fileBuilder = PluginProtos.CodeGeneratorResponse.File.newBuilder()
                        .setName(file.getName() + className + ".java")
                        .setContent(javaCode);

                responseBuilder.addFile(fileBuilder);
            }
        }
    }
}