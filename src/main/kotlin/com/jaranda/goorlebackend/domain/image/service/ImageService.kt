package com.jaranda.goorlebackend.domain.image.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.jaranda.goorlebackend.domain.image.error.FileDeleteException
import com.jaranda.goorlebackend.domain.image.error.FileUploadException
import com.jaranda.goorlebackend.shared.util.uuid
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class ImageService(
    @Value("\${aws.s3.bucket}")
    val bucket: String,
    @Value("\${aws.s3.region}")
    val region: String,
    val s3: AmazonS3,
) {
    val logger = LoggerFactory.getLogger(ImageService::class.java)

    fun uploadImages(files: List<MultipartFile>): MutableList<String> {
        val imageUrls = mutableListOf<String>()

        try {
            for (file in files) {
                val url = uploadImage(file)
                imageUrls.add(url)
            }
        } catch (e: Exception) {
            logger.error("Error while uploading images", e)
            for (url in imageUrls) deleteImage(url)
            throw FileUploadException()
        }
        return imageUrls
    }

    fun uploadImage(file: MultipartFile): String {
        val objectMetaData = ObjectMetadata()
        val fileName = "${uuid()}.${file.originalFilename!!.split(".").last()}"
        objectMetaData.contentType = file.contentType
        objectMetaData.contentLength = file.size
        s3.putObject(
            PutObjectRequest(bucket, fileName, file.inputStream, objectMetaData)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return s3.getUrl(bucket, fileName).toString()
    }

    fun deleteImage(url: String) {
        try {
            val fileName = url.split("/").last()
            s3.deleteObject(bucket, fileName)
        } catch (e: Exception) {
            logger.error("Error while deleting image", e)
            throw FileDeleteException()
        }
    }
}
