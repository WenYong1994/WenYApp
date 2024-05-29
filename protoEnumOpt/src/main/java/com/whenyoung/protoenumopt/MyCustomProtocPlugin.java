package com.whenyoung.protoenumopt;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.compiler.PluginProtos;

import java.io.IOException;

public class MyCustomProtocPlugin {

    public static void main(String[] args) throws IOException {
        // 从System.in读取CodeGeneratorRequest
        PluginProtos.CodeGeneratorRequest request = PluginProtos.CodeGeneratorRequest.parseFrom(System.in);

        // 处理.proto文件并生成代码
        PluginProtos.CodeGeneratorResponse.Builder responseBuilder = PluginProtos.CodeGeneratorResponse.newBuilder();
        process(request, responseBuilder);

        // 将CodeGeneratorResponse写入System.out
        responseBuilder.build().writeTo(System.out);
    }

    private static void process(PluginProtos.CodeGeneratorRequest request, PluginProtos.CodeGeneratorResponse.Builder responseBuilder) {
        // 在这里处理.proto文件并生成代码
        for (com.google.protobuf.DescriptorProtos.FileDescriptorProto file : request.getProtoFileList()) {
            for (DescriptorProtos.DescriptorProto message : file.getMessageTypeList()) {
                System.out.println("Message name: " + message.getName());
                String className = message.getName() + "Custom";
                String javaCode = "public class " + className + " {\n}\n";

                PluginProtos.CodeGeneratorResponse.File.Builder fileBuilder = PluginProtos.CodeGeneratorResponse.File.newBuilder()
                        .setName(file.getName() + className + ".java")
                        .setContent(javaCode);

                responseBuilder.addFile(fileBuilder);
            }
        }
    }
}