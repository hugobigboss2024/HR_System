import { createRouter, createWebHistory } from 'vue-router'
// import MainLayout from '../layout/Index.vue'
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import useTokenStore from '@/stores/token'

router.beforeEach((to, from, next) => {
  const tokenStore = useTokenStore()

  if (to.path !== '/login' && !tokenStore.token) {
    next('/login')
  } 
  else if (to.path === '/login' && tokenStore.token) {
    next('/home')
  } 
  else {
    next()
  }
})

const routes = [
    { path: '/login', component: LoginVue },
//     {
//     path: '/',
//     component: MainLayout,
//     redirect: '/dashboard',
//     children: [
//       {
//         path: 'dashboard',
//         name: 'Dashboard',
//         component: () => import('../views/Dashboard.vue'),
//         meta: { title: '數據看板' }
//       },
//       {
//         path: 'employees',
//         name: 'Employees',
//         component: () => import('../views/Employees.vue'),
//         meta: { title: '員工管理' }
//       },
//       {
//         path: 'leave',
//         name: 'Leave',
//         component: () => import('../views/Leave.vue'),
//         meta: { title: '請假管理' }
//       }
//     ]
//   }
    { 
        path: '/',
        component: LayoutVue,
        redirect: '/home', children:[
            { path: 'home', component: () => import('@/views/Home.vue') },
            { path: 'company/company', component: () => import('@/views/company/Company.vue') },
            { path: 'company/department', component: () => import('@/views/company/Department.vue') },
            { path: 'company/position', component: () => import('@/views/company/Position.vue') },
            { path: 'company/tag', component: () => import('@/views/company/Tag.vue') },
            { path: 'people/archives', component: () => import('@/views/people/Archives.vue') },
            { path: 'people/special', component: () => import('@/views/people/Special.vue') },
            { path: 'schedule/overview', component: () => import('@/views/schedule/Overview.vue') },
            { path: 'schedule/schedule', component: () => import('@/views/schedule/Schedule.vue') },
            { path: 'schedule/shift-template', component: () => import('@/views/schedule/ShiftTemplate.vue') },
            { path: 'schedule/schedule-items', component: () => import('@/views/schedule/ScheduleItems.vue') },
            { path: 'attendance/overview', component: () => import('@/views/attendance/Overview.vue') },
            { path: 'attendance/clock-data', component: () => import('@/views/attendance/ClockData.vue') },
            { path: 'attendance/timesheet-hk', component: () => import('@/views/attendance/TimesheetHK.vue') },
            { path: 'holiday/balance', component: () => import('@/views/holiday/Balance.vue') },
            { path: 'holiday/application', component: () => import('@/views/holiday/Application.vue') },
            { path: 'holiday/reports', component: () => import('@/views/holiday/Reports.vue') },
            { path: 'expense/application', component: () => import('@/views/expense/Application.vue') },
            { path: 'expense/types', component: () => import('@/views/expense/Types.vue') },
            { path: 'payroll/fixed', component: () => import('@/views/payroll/Fixed.vue') },
            { path: 'payroll/non-fixed', component: () => import('@/views/payroll/NonFixed.vue') },
            { path: 'payroll/employee-external', component: () => import('@/views/payroll/EmployeeExternal.vue') },
            { path: 'payroll/calculation-hk', component: () => import('@/views/payroll/CalculationHK.vue') },
            { path: 'payroll/settings', component: () => import('@/views/payroll/Settings.vue') },
            { path: 'business/dashboard', component: () => import('@/views/business/Dashboard.vue') }
        ]
    }
]
/**
/company {/company /department /position /tag}
/people {/archives /special}
/schedule {/overview /schedule /shift-template /schedule-items}
/attendance {/overview /clock-data /timesheet-hk}
/holiday {/balance /application /reports}
/expense {/application /types}
/payroll {/fixed /non-fixed /employee-external /calculation-hk /settings}
/business {/dashboard }
 */
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router