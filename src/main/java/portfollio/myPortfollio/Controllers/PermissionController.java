package portfollio.myPortfollio.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import portfollio.myPortfollio.Services.PermissionService;
import portfollio.myPortfollio.dtos.request.PermissionRequest;
import portfollio.myPortfollio.dtos.response.ApiResponse;
import portfollio.myPortfollio.dtos.response.PermissionResponse;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @GetMapping("")
    public ApiResponse<List<PermissionResponse>> getAllPermission() {
        List<PermissionResponse> permissions = permissionService.getAllPermisson();

        return ApiResponse.<List<PermissionResponse>>builder().data(permissions).build();
    }

    @PostMapping("")
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {

        PermissionResponse permission = permissionService.create(request);

        return ApiResponse.<PermissionResponse>builder().data(permission).build();
    }

    @DeleteMapping("/{permisison}")
    public ApiResponse<Void> deletePermission(@PathVariable String permisison) {
        permissionService.delete(permisison);

        return ApiResponse.<Void>builder().build();
    }
}
