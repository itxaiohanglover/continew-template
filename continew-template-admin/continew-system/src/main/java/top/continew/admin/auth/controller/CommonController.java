
package top.continew.admin.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.anno.Cached;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.continew.admin.common.constant.CacheConstants;
import top.continew.admin.system.enums.OptionCategoryEnum;
import top.continew.admin.system.model.query.OptionQuery;
import top.continew.admin.system.model.resp.file.FileUploadResp;
import top.continew.admin.system.service.*;
import top.continew.starter.core.util.validation.ValidationUtils;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;
import top.continew.starter.log.annotation.Log;

import java.io.IOException;
import java.util.List;

/**
 * 公共 API
 *
 * @author Charles7c
 * @since 2023/1/22 21:48
 */
@Tag(name = "公共 API")
@Log(ignore = true)
@Validated
@RestController("systemCommonController")
@RequiredArgsConstructor
@RequestMapping("/system/common")
public class CommonController {

    private final FileService fileService;
    private final DictItemService dictItemService;
    private final OptionService optionService;

    @Operation(summary = "上传文件", description = "上传文件")
    @Parameter(name = "parentPath", description = "上级目录", example = "/", in = ParameterIn.QUERY)
    @PostMapping("/file")
    public FileUploadResp upload(@RequestPart @NotNull(message = "文件不能为空") MultipartFile file,
                                 @RequestParam(required = false) String parentPath) throws IOException {
        ValidationUtils.throwIf(file::isEmpty, "文件不能为空");
        FileInfo fileInfo = fileService.upload(file, parentPath);
        return FileUploadResp.builder()
            .id(fileInfo.getId())
            .url(fileInfo.getUrl())
            .thUrl(fileInfo.getThUrl())
            .metadata(fileInfo.getMetadata())
            .build();
    }

    @Operation(summary = "查询字典", description = "查询字典列表")
    @Parameter(name = "code", description = "字典编码", example = "notice_type", in = ParameterIn.PATH)
    @GetMapping("/dict/{code}")
    public List<LabelValueResp> listDict(@PathVariable String code) {
        return dictItemService.listByDictCode(code);
    }


    @SaIgnore
    @Operation(summary = "查询系统配置参数", description = "查询系统配置参数")
    @GetMapping("/dict/option/site")
    @Cached(key = "'SITE'", name = CacheConstants.OPTION_KEY_PREFIX)
    public List<LabelValueResp<String>> listSiteOptionDict() {
        OptionQuery optionQuery = new OptionQuery();
        optionQuery.setCategory(OptionCategoryEnum.SITE.name());
        return optionService.list(optionQuery)
            .stream()
            .map(option -> new LabelValueResp<>(option.getCode(), StrUtil.nullToDefault(option.getValue(), option
                .getDefaultValue())))
            .toList();
    }

}
