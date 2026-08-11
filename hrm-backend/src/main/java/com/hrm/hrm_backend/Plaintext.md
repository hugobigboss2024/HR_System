├── anno/          <-- 自訂註解（例如：@RequirePermission 權限校驗、@Log 操作日誌）
├── config/        <-- 配置類（RedisConfig, MyBatisPlusConfig, CorsConfig）
├── controller/    <-- 控制層（SysUserController, EmployeeController, AttendanceController）
├── service/       <-- 業務邏輯層（SysUserService, EmployeeService）
│   └── impl/      <-- Service 實現類
├── mapper/        <-- MyBatis-Plus Mapper 介面（UserMapper, EmployeeMapper）
├── entity/        <-- 數據庫映射實體（SysUser, Employee, AttendanceRecord）
├── dto/           <-- 傳輸物件（LoginDTO, LeaveApplyDTO）
├── exception/     <-- 全局異常處理（GlobalExceptionHandler, CustomException）
├── interceptors/  <-- 攔截器（JwtInterceptor - 登入token校驗）
├── utils/         <-- 自訂工具類（JwtUtils, RedisUtils，搭配 Hutool 使用）
├── validation/    <-- 自訂參數校驗器（Validation）
├── file/          <-- 檔案上傳/下載邏輯 (Excel / 頭像上傳)
└── HrmApplication.java