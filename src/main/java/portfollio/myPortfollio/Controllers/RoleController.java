package portfollio.myPortfollio.Controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.RoleService;
import portfollio.myPortfollio.dtos.request.RoleRequest;
import portfollio.myPortfollio.dtos.response.ApiResponse;
import portfollio.myPortfollio.dtos.response.RoleResponse;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping("")
    public ApiResponse<List<RoleResponse>> getAllPermission() {
        List<RoleResponse> role = roleService.getAll();

        return ApiResponse.<List<RoleResponse>>builder()
                .data(role)
                .build();
    }

    @PostMapping("")
    public ApiResponse<RoleResponse> createPermission(@RequestBody RoleRequest request) {

        RoleResponse permission = roleService.create(request);

        return  ApiResponse.<RoleResponse>builder()
                .data(permission)
                .build();
    }

    @DeleteMapping("/{role}")
    public ApiResponse<Void> deletePermission(@PathVariable String role) {
        roleService.delete(role);

        return ApiResponse.<Void>builder()
                .build();
    }


}
